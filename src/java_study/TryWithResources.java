package java_study;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The try-with-resources statement is a try statement that declares one or more
 * resources. A resource is an object that must be closed after the program is
 * finished with it. The try-with-resources statement ensures that each resource
 * is closed at the end of the statement.
 * 
 * Any object that implements java.lang.AutoCloseable, which includes all
 * objects which implement java.io.Closeable, can be used as a resource.
 *
 * Note that the close methods of resources are called in the opposite order of
 * their creation.
 * 
 * You can retrieve suppressed exceptions by calling the Throwable.getSuppressed
 * method from the exception thrown by the try block.
 * 
 */

public class TryWithResources {

	public static void main(String[] args) {
		try (BufferedReader br =
                new BufferedReader(new FileReader("src\\java_study\\input.txt"));
				BufferedReader br2 =
		                new BufferedReader(new FileReader("src\\java_study\\output.txt"));
				) {
			
			System.out.println(br.readLine());
			System.out.println(br2.readLine());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
