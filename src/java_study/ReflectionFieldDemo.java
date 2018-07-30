package java_study;

import java.lang.reflect.Field;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ReflectionFieldDemo {
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

		Field[] fields = FieldSampleClass.class.getDeclaredFields();
		System.out.println("fields: " + fields);
		boolean isEquals = fields[0].equals(fields[1]);
		System.out.println("Fields are " + (isEquals ? "equal." : "not equal."));

		FieldSampleClass sampleObject = new FieldSampleClass();
		sampleObject.setSampleField("data");
/*
 *      This code does not work, because of missing anotations.
 *      
 *		Field field = FieldSampleClass.class.getField("sampleField");
 *		System.out.println("field.get(sampleObject): " + field.get(sampleObject));
 */
		
		
		FieldSampleClass2 sampleObject2 = new FieldSampleClass2();
		sampleObject2.setSampleField("data");

		Field field2 = FieldSampleClass2.class.getField("sampleField");
		System.out.println(field2.get(sampleObject2));		
	}
}

class FieldSampleClass {

	private String sampleField;
	private String sampleField1;


	public String getSampleField() {
		return sampleField;
	}

	public void setSampleField(String sampleField) {
		this.sampleField = sampleField;
	}

	public String getSampleField1() {
		return sampleField1;
	}

	public void setSampleField1(String sampleField1) {
		this.sampleField1 = sampleField1;
	}
}

@FieldCustomAnnotation(name = "SampleClass", value = "Sample Class Annotation")
class FieldSampleClass2 {

	@FieldCustomAnnotation(name = "sampleClassField", value = "Sample Field Annotation")
	public String sampleField;

	public String getSampleField() {
		return sampleField;
	}

	public void setSampleField(String sampleField) {
		this.sampleField = sampleField;
	}
}

@Retention(RetentionPolicy.RUNTIME)
@interface FieldCustomAnnotation {
	public String name();

	public String value();
}
