#include <jni.h>        // JNI header provided by JDK
#include "java_study_HelloJNI.h"   // Generated

public class HelloJNICpp {
   static {
      System.loadLibrary("hello"); // hello.dll (Windows) or libhello.so (Unixes)
   }
 
   // Native method declaration
   private native void sayHello();
 
   // Test Driver
   public static void main(String[] args) {
      new HelloJNICpp().sayHello();  // Invoke native method
   }
}
