package com.bungii.common.utilities;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;

public class LogUtility {

    /**
     * Overloaded constructor to initialize owner
     *
     * @param owner
     *            value of owner
     */
    public LogUtility(Object owner) {
        this.owner = owner;
        }




    /**
     * Default constructor
     */
    public LogUtility() {
        this("<Null>");
    }

    /**
     * Logs arguments as debug
     * 
     * @param varargs
     *            object of arguments
     */
    public void debug(Object... varargs) {
        logger.debug(CLASS+DELIM+owner + DELIM + toString(varargs));
    }
    /**
     * @return
     */
    public String getLogFileName() {
    	FileAppender appender = (FileAppender)logger.getRootLogger().getAppender("file");
    	return appender.getFile();
    	}

    /**
     * Logs arguments as trace
     * 
     * @param varargs
     *            object of arguments
     */
    public void trace(Object... varargs) {
        logger.trace(CLASS+DELIM+owner + DELIM + toString(varargs));
    }

    /**
     * Logs arguments as detail
     * 
     * @param varargs
     *            object of arguments
     */
    public void detail(Object... varargs) {
        logger.info(CLASS+DELIM+owner + DELIM + toString(varargs));
    }

    /**
     * Logs arguments as warning
     * 
     * @param varargs
     *            object of arguments
     */
    public void warning(Object... varargs) {
        logger.warn(CLASS+DELIM+owner + DELIM + toString(varargs));
    }

    /**
     * Logs arguments as error
     * 
     * @param varargs
     *            object of arguments
     */
    public void error(Object... varargs) {
        logger.error(CLASS+DELIM+owner + DELIM + toString(varargs));

    }

    //Need to update, throw exception after logging
    public void handleError(String msg, Object... varargs) {
        Exception rootCause = null;
        if (varargs.length != 0) {
            if (varargs[0] instanceof Exception) {
                rootCause = (Exception) varargs[0];
            }
        }
        String text = "Error: " + msg + DELIM + toString(varargs);
        error(text);

        if (rootCause != null ) {
            error(msg, rootCause.getStackTrace());
        }
    //    throw new Exception() ;
    }

    //Need to update, throw exception after logging
    public void handleError(String msg) throws Exception {
        error("Error: " + msg + DELIM + "Re-throwing exception");
        throw new Exception() ;
    }

    private static final String DELIM = "|";
    //added runner class parameter as in case of parallel run all logs are entered in same , adding runner class name will help in identifying runner class
    private static final String CLASS = System.getProperty("runner.class").equalsIgnoreCase("RunSuite")?"": System.getProperty("runner.class");
    private static String toString(Object... varargs) {
        String s = "", delim = "";
        for (Object obj : varargs) {
            s += delim + obj.toString();
            delim = DELIM;
        }
        return s;
    }


    private Logger logger = Logger.getLogger(this.getClass());
//    private static boolean logInitialized = false;
    private Object owner;

}
