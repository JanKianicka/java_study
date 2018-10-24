#include <jni.h>
#include "java_study_HelloJNICpp.h"
#include "HelloJNICppImpl.h"
 
JNIEXPORT void JNICALL Java_java_1study_HelloJNICpp_sayHello (JNIEnv *env, jobject thisObj) {
    sayHello();  // invoke C++ function
    return;
}
