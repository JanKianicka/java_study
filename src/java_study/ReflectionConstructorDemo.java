package java_study;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.lang.String;
/*
 * https://www.tutorialspoint.com/javareflect/java_reflect_constructor.htm
 */

public class ReflectionConstructorDemo {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		/*
		 * Originally there was Constructor[], where was an important warning: Warning:
		 * Constructor is a raw type. References to generic type Constructor<T> should
		 * be parameterized. And here is interesting discussion about this topic:
		 * https://stackoverflow.com/questions/2770321/what-is-a-raw-type-and-why-
		 * shouldnt-we-use-it
		 * 
		 * How's a raw type different from using <?> as a type parameter?
		 * 
		 * List<Object>, List<String>, etc are all List<?>, so it may be tempting to
		 * just say that they're just List instead. However, there is a major
		 * difference: since a List<E> defines only add(E), you can't add just any
		 * arbitrary object to a List<?>. On the other hand, since the raw type List
		 * does not have type safety, you can add just about anything to a List.
		 * 
		 */

		Constructor<?>[] constructors = ConstructorSampleClass.class.getConstructors();

		for (Constructor<?> constructor : constructors) {
			System.out.println("Constructor: " + constructor.getName());
			Class<?> declaringClass = constructor.getDeclaringClass();
			System.out.println("Declaring class:" + declaringClass.getName());
			Class<?>[] parameterTypes = constructor.getParameterTypes();
			for (Class<?> parameterType : parameterTypes) {
				System.out.println("Parameter:" + parameterType.getName());
				System.out.println("Parameter class:" + parameterType.getClass());
			}
			Type[] genParameterTypes = constructor.getGenericParameterTypes();
			for(Type type:genParameterTypes) {
				System.out.println("Type: "+ type.getClass());
			}
			if(parameterTypes.length == 1) {
				
				if(parameterTypes[0].getName().equals("java.lang.String")) {
					System.out.println("We have String type of input parameter and can create the object of this type.");
					ConstructorSampleClass sampleObject = (ConstructorSampleClass) constructor.newInstance("data");
				    System.out.println("  Call of getSampleField on sampleObject:  " + sampleObject.getSampleField());					
					
				}
			}
		}
		

		
	}
}

class ConstructorSampleClass {
	private String sampleField;

	public ConstructorSampleClass() {
	}

	public ConstructorSampleClass(String sampleField) {
		this.sampleField = sampleField;
	}

	public String getSampleField() {
		return sampleField;
	}

	public void setSampleField(String sampleField) {
		this.sampleField = sampleField;
	}
}
