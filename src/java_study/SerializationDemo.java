package java_study;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class EmployeeForSerialization implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;
	public String address;
	public transient int SSN;
	public int number;

	public void mailCheck() {
		System.out.println("Mailing a check to " + name + " " + address);
	}
	
	public long getSerialVersionUID() {
		return serialVersionUID;
	}
}

class SerializeDemo {

	public static final void serilize() {
		EmployeeForSerialization e = new EmployeeForSerialization();
		e.name = "Reyan Ali";
		e.address = "Phokka Kuan, Ambehta Peer";
		e.SSN = 11122333;
		e.number = 101;

		try {
			FileOutputStream fileOut = new FileOutputStream("employee.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(e);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in: employee.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}


class DeserializeDemo {

	public static final void deserialze() {
		EmployeeForSerialization e = null;
		try {
			FileInputStream fileIn = new FileInputStream("employee.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (EmployeeForSerialization) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();
			return;
		}

		System.out.println("Deserialized Employee...");
		System.out.println("Name: " + e.name);
		System.out.println("Address: " + e.address);
		System.out.println("SSN: " + e.SSN);
		System.out.println("Number: " + e.number);
		System.out.println("serialVersionUID: " + e.getSerialVersionUID());
	}
}

public class SerializationDemo {

	public static void main(String[] args) {
		SerializeDemo.serilize();
		DeserializeDemo.deserialze();
	}

}
