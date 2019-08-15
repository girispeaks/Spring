package com.test.automation.util;

import org.apache.log4j.Logger;

public final class LoggerUtil {
	
	/*Method to log the info message
	 * 
	 * @param log
	 * @param message
	 */
	public static void logInfo(Logger log, String message) {
		if(log.isDebugEnabled()) {
			log.info(message);
		}
	}
	
	/*Method to log the Debug message
	 * 
	 * @param log
	 * @param message
	 */
	public static void logDebug(Logger log, String message) {
		if(log.isDebugEnabled()) {
			log.debug(message);
		}
	}
	
	/*Method to log the error message
	 * 
	 * @param log
	 * @param message
	 */
	public static void logError(Logger log, String message) {	
			log.error(message);	
	}

}
