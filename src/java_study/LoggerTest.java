package java_study;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerTest {

	public static void main(String[] args) {
		// assumes the current class is called MyLogger
		final Logger LOGGER = Logger.getLogger(LoggerTest.class.getName());

        // set the LogLevel to Severe, only severe Messages will be written
        LOGGER.setLevel(Level.SEVERE);
        LOGGER.severe("Info Log");
        LOGGER.warning("Info Log");
        LOGGER.info("Info Log");
        LOGGER.finest("Really not important");

        // set the LogLevel to Info, severe, warning and info will be written
        // finest is still not written
        LOGGER.setLevel(Level.INFO);
        LOGGER.severe("Info Log");
        LOGGER.warning("Info Log");
        LOGGER.info("Info Log");
        LOGGER.finest("Really not important");
	}

}
