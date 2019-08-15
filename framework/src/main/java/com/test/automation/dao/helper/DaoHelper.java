package com.test.automation.dao.helper;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.naming.NamingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

//Class to retrieve the query object from XML string and fetch the response from the DB.

public class DaoHelper {

	public static final String DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final boolean cacheData = false;
	public static final boolean isMultipleRecords = true;
	public static final int defaultResultsCount = 10;
	public static final char argumentPrefix = '_';
	public static final String inputDataQuerySet = "DataQueries.xml";
	public static final URL targetPath = ClassLoader.getSystemResource("");

	// HashMap to keep the connectionStrings
	public static Map<String, String> databaseMap = new HashMap<>();

	// HashMap to keep the regionName
	public static Map<String, String> regionName = new HashMap<>();

	// HashMap to keep the data source of each query referred by its key
	public static Map<String, String> querySourceMap = new HashMap<>();

	// HashMap to keep the query script of each query referred by its key
	public static Map<String, String> queryStringMap = new HashMap<>();

	// HashMap to keep the cached test data
	public static HashMap<String, DaoHelper> dataRepository = new HashMap<String, DaoHelper>();

	static {
		try {
			populateDataSourceAndQuery();
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static String getDbConnection(String key) throws NamingException {
		if (!querySourceMap.containsKey(key)) {
			throw new NamingException("Missing Source Mapping for the Query " + key);
		}
		String db = querySourceMap.get(key).toUpperCase();
		if (!databaseMap.containsKey(db)) {
			throw new NamingException("Missing connectionString for the source " + db);
		}
		return databaseMap.get(db);
	}

	/*
	 * execute query
	 * 
	 * @param resultCount - amount of return records
	 * 
	 * @param connectionString - database connecting string
	 * 
	 * @param script - query
	 * 
	 * @param key - key in queries file
	 * 
	 * @param keyWithArguements - updated key
	 * 
	 * @param arguements - parameters in query
	 * 
	 * @return
	 */

	private static List<HashMap<String, String>> executeQuery(int resultCount, String connectionString, String script,
			String key, String keyWithArguements, Objects... arguements) {
		try (Connection con = DriverManager.getConnection(connectionString)) {
			long startTime = System.nanoTime();
			try (PreparedStatement statement = con.prepareStatement(script)) {
				if (arguements.length > 0) {
					for (int i = 0; i < arguements.length; i++) {
						setArguement(statement, i + 1, arguements[i]);
					}
				}
				try (ResultSet rs = statement.executeQuery()) {
					long endTime = System.nanoTime();
					ResultSetMetaData metadata = rs.getMetaData();
					int columnCount = metadata.getColumnCount();
					String[] columns = new String[columnCount];
					List<String[]> rows = new ArrayList<String[]>();
					for (int i = 1; i < columnCount; i++) {
						columns[i] = metadata.getColumnName(i + 1);
					}
					while (rs.next()) {
						String[] row = new String[columnCount];
						for (int i = 0; i < columnCount; i++) {
							String value = rs.getString(i + 1);
							row[i] = (value == null || value.compareToIgnoreCase("null") == 0) ? null : value;
						}
						rows.add(row);
					}

					String[][] results = new String[rows.size()][];
					DaoHelper newTestData = new DaoHelper(key, columns, rows.toArray(results));
					if (cacheData) {
						if (dataRepository.containsKey(keyWithArguements))
							dataRepository.remove(keyWithArguements);
						dataRepository.put(keyWithArguements, newTestData);
					}
					double timespan = (endTime - startTime) / 1000000000.0;
					// ReportLogger.logDebug("---------Time taken for the DB query " + key + ":" +
					// timespan + "seconds -----");
					return newTestData.getData(resultCount);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Function to run simple query identified by the key.
	 * 
	 * @param key
	 *            - The unique identifier the query to be executed.
	 * @param inClauses
	 *            - IN clause statement in query
	 * @return List of KeyValue pairs if script is executed successfully or 'null'
	 *         if failed. Each HashMap<String, String> would be used to keep a row
	 *         of the return query table.
	 * @throws NamingException
	 */
	public static List<HashMap<String, String>> executeQuery(String key, String... inClauses) throws NamingException {
		if (inClauses.length > 0)
			return executeQuery(key, false, 1, inClauses);
		else
			return executeQuery(key, cacheData, 1);
	}

	/**
	 * Function to run simple query identified by the key.
	 * 
	 * @param key
	 *            - The unique identifier the query to be executed.
	 * @param useCacheData
	 *            - Specify if keep the query data for future use.
	 * @param resultCount
	 *            - Specify how many data rows shall be returned instead of the
	 *            default value of '1'
	 * @param inClauses
	 *            - In clause statement in query
	 * @return List of keyValue pairs if script is executed successfully, or 'null'
	 *         if failed. Each HashMap<String, String> would be used to keep a row
	 *         of the return query table.
	 * @throws NamingException
	 */
	public static List<HashMap<String, String>> executeQuery(String key, boolean useCacheData, int resultCount,
			String... inClauses) throws NamingException {
		if (useCacheData && dataRepository.containsKey(key)) {
			DaoHelper data = dataRepository.get(key);
			if (data != null && data.hasData()) {
				// If there is data already cached, return it directly
				return data.getData(resultCount);
			}
			// Otherwise, no need to keep invalid data any longer
			dataRepository.remove(key);
		}
		String connectionString = getDbConnection(key);

		String script;
		if (!queryStringMap.containsKey(key)) {
			throw new NamingException("Failed to get script of query " + key);
		}
		script = queryStringMap.get(key);
		if (resultCount > 1 || isMultipleRecords) {
			script = script.replaceFirst("(?i)top\\s*\\d+", "top " + Math.max(defaultResultsCount, resultCount));
		}
		if (inClauses.length > 0) {
			for (String inClause : inClauses) {
				script = script.replaceFirst("\\?", inClause);
			}
		}
		return executeQuery(resultCount, connectionString, script, key, key);
	}

	// Unique key to identify the query mechanism(Database, ConnectionString, SQL
	// Script) and result of cached test data.
	private String key;

	// Names of the test data
	private String[] columns;

	// Array to keep the actual data corresponded with the columns.
	private String[][] resultRows;

	// Index of the next row to be returned to test data consumers.
	private int rowIndex = -1;

	public String getKey() {
		return key;
	}

	/**
	 * Method to encapsulate the retrieval of ConnectionString identified by the
	 * key. Notice this function is not supposed to be called directly, thus no
	 * checking of if the key is valid or contained.
	 * 
	 * @return ConnectionString pre-loaded and associated with the key.
	 */
	public String getConnectionString() {
		String database = databaseMap.get(key);
		return querySourceMap.get(database);
	}

	/**
	 * Method to retrieve the SQL query identified by the key. Notice this function
	 * is not supposed to be called directly, thus no checking of if the key is
	 * valid or contained.
	 * 
	 * @return The query script associated with the key.
	 */
	public String getScript() {
		return queryStringMap.get(key);
	}

	/*
	 * Method to retrieve the column names of the returned data.
	 * 
	 * @return The string array to keep the column names of the returned data.
	 */
	public String[] getColumns() {
		return columns;
	}

	/*
	 * Method to retrieve the returned data rows.
	 * 
	 * @return All rows of test data, each row as a String[].
	 */
	public String[][] getResultRows() {
		return resultRows;
	}

	/*
	 * Get the current index of the result rows. Notice it is not supposed to be
	 * called directly by TestData consumer/DataProvider
	 * 
	 * @return The index of String[][] resultRows, 0 for the first row.
	 */
	public int getResultRowIndex() {
		return rowIndex;
	}

	/*
	 * Get the total row number
	 * 
	 * @return the row count.
	 */
	public int getRowCount() {
		return resultRows.length;
	}

	/*
	 * If there is a test data returned by executing the query
	 * 
	 * @return 'true' for yes, 'false' for no
	 */
	public boolean hasData() {
		return columns != null && resultRows != null && resultRows.length > 0;
	}

	/*
	 * Compose a HashMap to return the row identified by rowIndex.
	 * 
	 * @return The current row of test data as a HashMap<String, String>
	 */
	public HashMap<String, String> getNext() {
		if (!hasData()) {
			return null;
		}

		// Notice that the cached data could be reused after the rowIndex reaching the
		// last row.
		rowIndex = (++rowIndex) % resultRows.length;
		String[] row = resultRows[rowIndex];
		int rowLength = row.length;
		if (rowLength != columns.length) {
			return null;
		}
		HashMap<String, String> result = new HashMap<>();
		for (int i = 0; i < rowLength; i++) {
			result.put(columns[i], row[i]);
		}
		return result;
	}

	/*
	 * Return multiple rows of test data as a list of HashMap. Notice that if there
	 * are less rows of data than 'count', then the returned list may have a length
	 * equal to rowCount.
	 * 
	 * @param count - rows to be returned.
	 * 
	 * @return A list of HashMap<String, String>, each for a row of test data.
	 */
	public List<HashMap<String, String>> getData(int count) {
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		// if there is data already cached, return it directly
		count = Math.min(count, getRowCount());

		for (int i = 0; i < count; i++) {
			HashMap<String, String> currentData = getNext();
			result.add(currentData);
		}
		return result;
	}

	public static Connection getConnection(String source) {
		Connection connection = null;
		try {
			String connectionString = databaseMap.get(source);
			connection = DriverManager.getConnection(connectionString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static String getConnectionString(String source) {
		String connectionString = null;
		try {
			connectionString = databaseMap.get(source);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connectionString;
	}

	/*
	 * This function should be run once only to parse the DataQueries.xml to get all
	 * connectionStrings, SQL scripts and their sources.
	 */
	private static void populateDataSourceAndQuery() {
		// get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			// Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			URL url = new URL(targetPath, inputDataQuerySet);
			InputStream input = url.openStream();
			// parse using builder to get DOM representation of the XML file
			Document dom = db.parse(input);
			Element root = dom.getDocumentElement();

			// Get Data sources
			Element container = (Element) root.getElementsByTagName("Sources").item(0);
			NodeList items = container.getElementsByTagName("Source");

			if (items == null || items.getLength() == 0)
				throw new Exception("Failed to find Data query Source definitions");

			int count = items.getLength();
			for (int i = 0; i < count; i++) {
				Element connection = (Element) items.item(i);
				String name = connection.getAttributes().getNamedItem("name").getNodeName();
				String s = connection.getAttributes().getNamedItem("connectionString").getNodeValue();
				// String regionName = s.substring(s.indexOf("sqlserver:")+2,
				// s.indexOf(".biabau"));
				databaseMap.put(name, s);
			}
			// Get Data Queries
			container = (Element) root.getElementsByTagName("Queries").item(0);
			items = container.getElementsByTagName("Query");
			count = items.getLength();
			for (int i = 0; i < count; i++) {
				Element query = (Element) items.item(i);
				NamedNodeMap attributes = query.getAttributes();
				String key = attributes.getNamedItem("key").getNodeValue();
				String source = attributes.getNamedItem("source").getNodeValue();
				// using attribute to store the SQL script to utilize the escape and un-escape
				// special characters automatically
				String script = attributes.getNamedItem("script").getNodeValue();
				querySourceMap.put(key, source);
				queryStringMap.put(key, script);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Private constructor to cache the test data after executing q query.
	 * 
	 * @param key - The unique identifier the query to be executed.
	 * 
	 * @param columns - The String Array to keep the column names of the returned
	 * data.
	 * 
	 * @param resultRows - All rows of test data, each row as a String[].
	 */
	private DaoHelper(String key, String[] columns, String[][] resultRows) {
		this.key=key;
		this.columns=columns;
		this.resultRows=resultRows;
	}
	
	
	/*
	 * Function to prepare parameter for PreparedStatement.
	 * @param statement - PreparedStatement to be executed.
	 * @param parameterIndex - Index of the arguement. ('1' for the first, not '0')
	 * @param x - The current arguement.
	 * @throws SQLException
	 */
	private static void setArguement(PreparedStatement statement, int parameterIndex, Object x) throws SQLException {
		if (x == null) {
			statement.setNull(parameterIndex, java.sql.Types.VARCHAR);
		} else {
			Class<?> clazz = x.getClass();
			String className = clazz.getSimpleName().toLowerCase();
			switch (className) {
			case "string":
				statement.setString(parameterIndex, (String) x);
				break;
			case "boolean":
				statement.setBoolean(parameterIndex, (boolean) x);
				break;
			case "byte":
				statement.setByte(parameterIndex, (byte) x);
				break;
			case "short":
				statement.setShort(parameterIndex, (short) x);
				break;
			case "integer":
				statement.setInt(parameterIndex, (int) x);
				break;
			case "long":
				statement.setLong(parameterIndex, (long) x);
				break;
			case "float":
				statement.setFloat(parameterIndex, (float) x);
				break;
			case "double":
				statement.setDouble(parameterIndex, (double) x);
				break;
			case "time":
				statement.setTime(parameterIndex, (Time) x);
				break;
			default:
				statement.setString(parameterIndex, x.toString());
			}
		}
	}

	public static String retrieveValueFromMap(List<HashMap<String, String>> resultList, String key,
			boolean... isMultipleIterate) {
		StringBuilder value = new StringBuilder();
		if (resultList != null && !resultList.isEmpty()) {
			if (isMultipleIterate.length > 0 && isMultipleIterate[0]) {
				for (HashMap<String, String> resultMap : resultList) {
					if (value.length() > 0) {
						value.append("|");
					}
					if (resultMap.containsKey(key)) {
						value.append(resultMap.get(key));
					}
				}
			} else {
				HashMap<String, String> resultMap = resultList.get(0);
				value = new StringBuilder();
				if (resultMap.containsKey(key))
					;
				{
					value.append(resultMap.get(key));
				}
			}
		}
		return value.toString();
	}

}
