#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_example_albertheijnsampleapp_di_AppModule_getKey(JNIEnv *env, jobject thiz) {
    return (*env)-> NewStringUTF(env, "live_o6BUl8pmNdCn3eHmRcIYFgn3R7JpsPl2yEn7fC10mmeAAr5nXphNv5HfzOWzGzqZ");
}