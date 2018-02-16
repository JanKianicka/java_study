package java_study;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadData_Demo {

   public static void main(String args[]) {
      FileReader fr = null;		
      try {
         File file = new File("file.txt");
         fr = new FileReader(file); char [] a = new char[50];
         fr.read(a);   // reads the content to the array
         for(char c : a)
         System.out.print(c);   // prints the characters one by one
      } catch (IOException e) {
    	  System.out.println("I am in catch block with resources.");
         e.printStackTrace();
      }finally {
         try {
        	System.out.println("I am in finally block with resources.");
            fr.close();
            System.out.println("Non existing file closed.");
         } catch (IOException ex) {
        	System.out.println("I am in finally block of try-catch with resources.");
            ex.printStackTrace();
         }
      }
   }
}
