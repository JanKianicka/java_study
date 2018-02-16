package java_study;

import java.io.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;
public class ByteStreamTest {

   public static void main(String args[])throws IOException {
      ByteArrayOutputStream bOutput = new ByteArrayOutputStream(12);

      while( bOutput.size()!= 10 ) {
         // Gets the inputs from the user
         bOutput.write("hello".getBytes()); 
      }
      byte b [] = bOutput.toByteArray();
      System.out.println("Print the content");
      
      for(int x = 0 ; x < b.length; x++) {
         // printing the characters
         System.out.print((char)b[x]  + "   "); 
      }
      System.out.println("   ");
      
      int c;
      ByteArrayInputStream bInput = new ByteArrayInputStream(b);
      System.out.println("Converting characters to Upper case " );
      
      for(int y = 0 ; y < 1; y++) {
         while(( c = bInput.read())!= -1) {
            System.out.println(Character.toUpperCase((char)c));
         }
         bInput.reset(); 
      }
      BoolStreamTest.testBooleanStream();
      
   }
   
   public static class BoolStreamTest {
	   
	   
	   public static void testBooleanStream() {
		   boolean[] foo = new boolean[4];
		   foo[0] = true;
		   foo[1] = false;
		   foo[2] = false;
		   foo[3] = true;
		   boolean[] foo2 = {false,false,false,true};
		   
		   Stream<Boolean> stream = IntStream.range(0, foo.length)
                   .mapToObj(idx -> foo[idx]);
		   stream.forEach(System.out::println);
		   // This does not work due to, that the stream was already operated and closed.
		   // Object[] a = stream.toArray();
		   // System.out.println("Length of array: " + a.length );
		   
		   System.out.println(stream);
		   
	   }
	   
	   
    }
}