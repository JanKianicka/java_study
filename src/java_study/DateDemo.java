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

/*
 * Date formating 
 * 
 * 
Character 	Description 	Example
G 	Era designator 	AD
y 	Year in four digits 	2001
M 	Month in year 	July or 07
d 	Day in month 	10
h 	Hour in A.M./P.M. (1~12) 	12
H 	Hour in day (0~23) 	22
m 	Minute in hour 	30
s 	Second in minute 	55
S 	Millisecond 	234
E 	Day in week 	Tuesday
D 	Day in year 	360
F 	Day of week in month 	2 (second Wed. in July)
w 	Week in year 	40
W 	Week in month 	1
a 	A.M./P.M. marker 	PM
k 	Hour in day (1~24) 	24
K 	Hour in A.M./P.M. (0~11) 	10
z 	Time zone 	Eastern Standard Time
' 	Escape for text 	Delimiter
" 	Single quote 	`

 * 
 * 
 * */
