package SeleniumTraining.PHP;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class Log4jExample{

   /* Get actual class name to be printed on */
	
   static Logger log = Logger.getLogger(Log4jExample.class.getName());
   
   public static void main(String[] args)throws IOException,SQLException{
	   PropertyConfigurator.configure("F:\\Selenium_Workspace\\PHP\\log4j.properties");
	   System.out.println(log);
	   
	   log.debug("Hello this is a debug message");
      log.info("Hello this is an info message");
   }
}
