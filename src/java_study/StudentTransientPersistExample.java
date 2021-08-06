package java_study;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class StudentTransientPersistExample {

	public static void main(String[] args) {

		StudentTransient s1 = new StudentTransient(211, "ravi", 22);// creating object
		// writing object into file
		try (FileOutputStream f = new FileOutputStream("f.txt"); ObjectOutputStream out = new ObjectOutputStream(f)){
			out.writeObject(s1);
			out.flush();

			out.close();
			f.close();
			System.out.println("success");			
		} catch (Exception e) {
			System.out.print(e);
			
		}
	}

}
