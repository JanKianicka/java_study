package java_study;

/* 
 * Integration of CPP
 * 
 * Cygwind with mingw32
 * > cd /cygdrive/c/repo/java_study/src
 * > javac java_study/HelloJNICpp.java
 * > javac -h HelloJNICpp
 * > cd ..
 * > javah -d java_study java_study.HelloJNICpp
 * > x86_64-w64-mingw32-g++ -I/cygdrive/c/java8/include -I/cygdrive/c/java8/include/win32 -I/cygdrive/c/repo/java_study/src/java_study/ -shared -o helloCpp.dll HelloJNICpp.c HelloJNICppImpl.cpp
 * We need dynamically linked libraries in the path
 * > export PATH=$PATH:/usr/x86_64-w64-mingw32/sys-root/mingw/bin
 * > java -Djava.library.path=java_study java_study.HelloJNICpp
 *   Hello World from C++!
 * 
 */

public class HelloJNICpp {
	static {
		System.loadLibrary("helloCpp"); // helloCpp.dll (Windows) or libhelloCpp.so (Unixes)
	}

	// Native method declaration
	private native void sayHello();

	// Test Driver
	public static void main(String[] args) {
		new HelloJNICpp().sayHello(); // Invoke native method
	}
}
