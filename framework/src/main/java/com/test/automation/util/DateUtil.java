package com.test.automation.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateUtil {
	
	static final int MOVE_POINT_MILLI = 3;
	private static final LocalDate minDate = LocalDate.of(1900, 1, 1);
	private static final LocalDate maxDate = LocalDate.of(2010, 1, 1);
	private static Random random=new Random();
	
	public static XMLGregorianCalendar toLocalXMLCalendar(Date date) throws Exception{
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
		xmlGregorianCalendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
		return xmlGregorianCalendar;
	}
	
	
	/*
	 * Converts the specified {@code Calendar} to {@code XMLGregorianCalendar}.
	 * <p>A local XML calendar has no timezone offset. The time-zone in the {@code Calendar} is ignored and the
	 * timezone field in the returned {@code XMLGregorianCalendar}  is set to {@link DatatypeConstants#FIELD_UNDEFINED}.
	 * 
	 * @param cal {@code Calendar} to convert. Can be {@code null}.
	 * @return {@code XMLGregorianCalendar} instance or {@code null}.
	*/
	public static XMLGregorianCalendar toLocalXMLCalendar(Calendar cal) {
		if(cal==null) {
			return null;
		}
		
		int year = cal.get(Calendar.YEAR);
		if(cal.get(Calendar.ERA)==GregorianCalendar.BC) {
			year=-year;
		}
		
		int millisecond = cal.get(Calendar.MILLISECOND);
		BigDecimal fractionalSecond = BigDecimal.valueOf(millisecond).movePointLeft(MOVE_POINT_MILLI);
		
		DatatypeFactory factory;
		try {
			factory = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			throw new IllegalStateException("Failed to obtain DatatypeFactory", e);
		}
		
		return factory.newXMLGregorianCalendar(BigInteger.valueOf(year), 
				cal.get(Calendar.MONDAY)+1, 
				cal.get(Calendar.DAY_OF_MONTH), 
				cal.get(Calendar.HOUR_OF_DAY), 
				cal.get(Calendar.MINUTE), 
				cal.get(Calendar.SECOND), 
				fractionalSecond, 
				DatatypeConstants.FIELD_UNDEFINED);
	}
	
	/*
	 * Returns the current date with the specified amount (positive or negative) added to the specified calendar field.
	 */
	public static Calendar todayAdd(int field, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.add(field, amount);
		return cal;
	}
	
	public static LocalDate getRandomDate() {
		int minDay = (int) minDate.toEpochDay();
		int maxDay = (int) maxDate.toEpochDay();
		long randamDay = minDay + random.nextInt(maxDay-minDay);
		return LocalDate.ofEpochDay(randamDay);
	}
	
	public static Calendar castStringToCalendar(String date, String format) throws Exception {
		if(date!=null) {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			cal.setTime(sdf.parse(date));
			return cal;
		}
		return null;
	}
	
	public static String castObjectToDate(Object calObj, String format) {
		SimpleDateFormat sdfFormat = new SimpleDateFormat(format);
		Calendar cal = (GregorianCalendar) calObj;
		Calendar defaultCal = Calendar.getInstance();
		defaultCal.set(0000, 00, 00);
		if(null!=cal) {
			return sdfFormat.format(cal.getTime());
		} else {
			return sdfFormat.format(defaultCal.getTime());
		}
	}
	
	public static String castCalendarToString(Calendar cal, String format) {
		if(cal !=null) {
			SimpleDateFormat format1 = new SimpleDateFormat(format);
			return format1.format(cal.getTime());
		}
		return null;
	}
	
	public static int dayDiffWithCurrentDate(Calendar testDate) {
		Calendar currentDate = Calendar.getInstance();
		currentDate.getTime();
		Calendar date = (Calendar) testDate.clone();
		int daysBetween = 0;
		
		while(date.before(currentDate)) {
			date.add(Calendar.DAY_OF_MONTH, 1);
			daysBetween++;
		}
		return (daysBetween-1);
	}
		
}
