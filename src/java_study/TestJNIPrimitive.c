#include <jni.h>
#include <stdio.h>
#include "java_study_TestJNIPrimitive.h"
 
JNIEXPORT jdouble JNICALL Java_java_1study_TestJNIPrimitive_average
          (JNIEnv *env, jobject thisObj, jint n1, jint n2) {
   jdouble result;
   printf("In C, the numbers are %d and %d\n", n1, n2);
   result = ((jdouble)n1 + n2) / 2.0;
   // jint is mapped to int, jdouble is mapped to double
   return result;
}

JNIEXPORT jstring JNICALL Java_java_1study_TestJNIPrimitive_sayHello
          (JNIEnv *env, jobject thisObj, jstring inJNIStr) {
   // Step 1: Convert the JNI String (jstring) into C-String (char*)
  const char *inCStr;
  inCStr = (*env)->GetStringUTFChars(env, inJNIStr, NULL);
  if (NULL == inCStr) return NULL;
 
   // Step 2: Perform its intended operations
  printf("In C, the received string is: %s\n", inCStr);
  (*env)->ReleaseStringUTFChars(env, inJNIStr, inCStr);  // release resources
 
  // Prompt user for a C-string
  char outCStr[128];
  printf("Enter a String: ");
  scanf("%s", outCStr);    // not more than 127 characters
 
  // Step 3: Convert the C-string (char*) into JNI String (jstring) and return
  return (*env)->NewStringUTF(env, outCStr);
}

