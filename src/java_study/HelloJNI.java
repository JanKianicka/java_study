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

How have I build and ran it on my local machine:
    > cd /cygdrive/c/repo/java_study/src
    > javah -d java_study java_study.HelloJNI
    output HelloJNI.class, java_study_HelloJNI.h
    > cd /cygdrive/c/repo/java_study/src/java_study
    > x86_64-w64-mingw32-gcc -Wl,--add-stdcall-alias -I/cygdrive/c/java8/include -I/cygdrive/c/java8/include/win32 -I/cygdrive/c/repo/java_study/src/java_study/ -shared -o hello.dll HelloJNI.c
    output is hello.dll and we can execute the java call.
    
    Description:
    -Wl: The -Wl to pass linker option --add-stdcall-alias to prevent UnsatisfiedLinkError (symbols with a stdcall suffix 
    (@nn) will be exported as-is and also with the suffix stripped). (Some people suggested to use -Wl,--kill-at.)
    
    -D __int64="long long": define the type (add this option in front if error "unknown type name '__int64'")    
    
    Watch out, this stupis windows JNI has named the function:
    'Java_java_1study_HelloJNI_sayHello', and this name has to be put to the .c file.
    
    Executed like this:
    > cd /cygdrive/c/repo/java_study/src
    > $ java -Djava.library.path=java_study java_study.HelloJNI
    Hello World!
    
    Setting the library path for Eclipse is in:
    Specifically: select Project, right click -> Properties / Java Build Path / Libraries tab, 
    select a .jar, expand it, 
    select Native library location, click Edit, folder chooser dialog will appear).
    
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
