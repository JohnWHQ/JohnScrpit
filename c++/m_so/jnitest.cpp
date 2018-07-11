#include "jni.h"
#include "hello.h"
#include <stdio.h>
JNIEXPORT void JNICALL Java_hello_test(JNIEnv *env,jobject obj){
    printf("Hello World! jni fucking done\n");
    return;
}
