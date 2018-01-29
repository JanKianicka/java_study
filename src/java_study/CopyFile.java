package java_study;

import java.io.*;
public class CopyFile {

   public static void main(String args[]) throws IOException {  
      FileInputStream in = null;
      FileOutputStream out = null;

      try {
         in = new FileInputStream("src\\java_study\\input.txt");
         out = new FileOutputStream("src\\java_study\\output.txt");
         // To get input stream
         File file = new File("C:\\repo\\java_study\\src\\java_study\\input.txt");
         System.out.println(file.getAbsolutePath());
         
         int c;
         while ((c = in.read()) != -1) {
            out.write(c);
         }
      }finally {
         if (in != null) {
            in.close();
         }
         if (out != null) {
            out.close();
         }
      }
   }
}
