package java_study;

/*The static initializer invokes System.loadLibrary() to load the native library "hello" 
(which contains a native method called sayHello()) during the class loading. 
It will be mapped to "hello.dll" in Windows; or "libhello.so" in Unixes/Mac OS X. 
This library shall be included in Java's library path (kept in Java system variable java.library.path). 
You could include the library into Java's library path via VM argument -Djava.library.path=/path/to/lib. 
The program will throw a UnsatisfiedLinkError if the library cannot be found in runtime. 
*/

/* The naming convention for the C function is Java_{package_and_classname}_{function_name}(JNI_arguments). 
The dot in package name is replaced by underscore.

The arguments are:

    JNIEnv*: reference to JNI environment, which lets you access all the JNI functions.
    jobject: reference to "this" Java object.

*/

public class HelloJNI {
	static {
		System.loadLibrary("hello"); // Load native library hello.dll (Windows) or libhello.so (Unixes)
										// at runtime
										// This library contains a native method called sayHello()
	}

	// Declare an instance native method sayHello() which receives no parameter and
	// returns void
	private native void sayHello();

	// Test Driver
	public static void main(String[] args) {
		new HelloJNI().sayHello(); // Create an instance and invoke the native method
	}

}
