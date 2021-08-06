package java_study;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class StudentTransientDePersist {

	public static void main(String[] args) {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("f.txt"));){
			StudentTransient s = (StudentTransient) in.readObject();
			System.out.println(s.id + " " + s.name + " " + s.age);
			in.close();
			
		} catch (Exception e) {
			System.out.print(e);
		}
		
	}
}
