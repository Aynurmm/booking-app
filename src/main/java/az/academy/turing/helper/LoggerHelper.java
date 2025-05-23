package az.academy.turing.helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoggerHelper {
    private static final Logger logger=LoggerFactory.getLogger(LoggerHelper.class);
    public static void info(String message){
        logger.info(message);
    }
    public static void error(String message){
        logger.error(message);
    }
    public static void warn(String message){
        logger.error(message);
    }
    public static void debug(String message){
        logger.debug(message);
    }
}
