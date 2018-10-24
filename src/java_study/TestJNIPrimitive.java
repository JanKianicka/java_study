package java_study;

/*
 * 
    1. Java Primitives: jint, jbyte, jshort, jlong, jfloat, jdouble, jchar, jboolean for 
       Java Primitive of int, byte, short, long, float, double, char and boolean, respectively.
    2. Java Reference Types: jobject for java.lang.Object. It also defines the following sub-types:
        jclass for java.lang.Class.
        jstring for java.lang.String.
        jthrowable for java.lang.Throwable.
        jarray for Java array. Java array is a reference type with eight primitive array and one Object array. 
        Hence, there are eight array of primitives jintArray, jbyteArray, jshortArray, jlongArray, jfloatArray, 
        jdoubleArray, jcharArray and jbooleanArray; and one object array jobjectArray.
 * 
 * The native programs:

    1. Receive the arguments in JNI type (passed over by the Java program).
    2. For reference JNI type, convert or copy the arguments to local native types, e.g., jstring to a C-string, jintArray to C's int[], and so on. Primitive JNI types such as jint and jdouble do not need conversion and can be operated directly.
    3. Perform its operations, in local native type.
    4. Create the returned object in JNI type, and copy the result into the returned object.
    5. Return.

 * 
 * Passing strings is more complicated than passing primitives, as Java's String is an object (reference type), 
 * while C-string is a NULL-terminated char array. 
 * You need to convert between Java String (represented as JNI jstring) and C-string (char*).
 * 
 * The JNI Environment (accessed via the argument JNIEnv*) provides functions for the conversion:

    To get a C-string (char*) from JNI string (jstring), 
       invoke method const char* GetStringUTFChars(JNIEnv*, jstring, jboolean*).
    To get a JNI string (jstring) from a C-string (char*), 
       invoke method jstring NewStringUTF(JNIEnv*, char*).
 * 
 * 
 * 
 * Successful:
 * > java -Djava.library.path=java_study java_study.TestJNIPrimitive
     In Java, the average is 2.5
     In C, the numbers are 3 and 2
 *
 * After extension to String input argument, successful but really mingled:
 * $ java -Djava.library.path=java_study java_study.TestJNIPrimitive
     In Java, the average is 2.5
     DDD
     In Java, the returned string is: DDD
     In C, the numbers are 3 and 2
     In C, the received string is: Hello from Java
     Enter a String:
   $
 *  
 */

public class TestJNIPrimitive {
	static {
		System.loadLibrary("myjni"); // myjni.dll (Windows) or libmyjni.so (Unixes)
	}

	// Declare a native method average() that receives two ints and return a double
	// containing the average
	private native double average(int n1, int n2);
	
	private native String sayHello(String msg);
	
	// Test Driver
	public static void main(String args[]) {
		System.out.println("In Java, the average is " + new TestJNIPrimitive().average(3, 2));
	    String result = new TestJNIPrimitive().sayHello("Hello from Java");
	    System.out.println("In Java, the returned string is: " + result);		
	}
}

/*
 * String functions in JNI environment.
 * 
//UTF-8 String (encoded to 1-3 byte, backward compatible with 7-bit ASCII)/
//Can be mapped to null-terminated char-array C-string
const char * GetStringUTFChars(JNIEnv *env, jstring string, jboolean *isCopy);
// Returns a pointer to an array of bytes representing the string in modified UTF-8 encoding.
void ReleaseStringUTFChars(JNIEnv *env, jstring string, const char *utf);
// Informs the VM that the native code no longer needs access to utf.
jstring NewStringUTF(JNIEnv *env, const char *bytes);
// Constructs a new java.lang.String object from an array of characters in modified UTF-8 encoding.
jsize GetStringUTFLength(JNIEnv *env, jstring string);
// Returns the length in bytes of the modified UTF-8 representation of a string.
void GetStringUTFRegion(JNIEnv *env, jstring str, jsize start, jsize length, char *buf);
// Translates len number of Unicode characters beginning at offset start into modified UTF-8 encoding 
// and place the result in the given buffer buf.

//Unicode Strings (16-bit character)
const jchar * GetStringChars(JNIEnv *env, jstring string, jboolean *isCopy);
// Returns a pointer to the array of Unicode characters
void ReleaseStringChars(JNIEnv *env, jstring string, const jchar *chars);
// Informs the VM that the native code no longer needs access to chars.
jstring NewString(JNIEnv *env, const jchar *unicodeChars, jsize length);
// Constructs a new java.lang.String object from an array of Unicode characters.
jsize GetStringLength(JNIEnv *env, jstring string);
// Returns the length (the count of Unicode characters) of a Java string.
void GetStringRegion(JNIEnv *env, jstring str, jsize start, jsize length, jchar *buf);
// Copies len number of Unicode characters beginning at offset start to the given buffer buf
*/