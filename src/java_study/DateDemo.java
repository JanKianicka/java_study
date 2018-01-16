package java_study;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDemo {

   public static void main(String args[]) {
      // Instantiate a Date object
	  // gets current date
	  Date date = new Date();
      
      // display time and date using toString()
      System.out.println(date.toString());
      
      // Formater
      SimpleDateFormat ft = 
    	      new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
      
      System.out.println("Current Date: " + ft.format(date));
      
      // Epoch time handling
      long currentEpoch = date.getTime()/1000; // divided by 1000 to get seconds from mseconds
      System.out.println("Current date in Epoch: " + currentEpoch);
      
      // In linux you can get these times using date command
      // date +%s     to get current epoch
      // date -d @1516109832      to get date from epoch in seconds
      
   }
}

