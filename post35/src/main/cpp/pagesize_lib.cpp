#include <jni.h>
#include <string>
#include <unistd.h> // For getpagesize()
#include <android/log.h> // For logging

#define LOG_TAG "PageSizeNDK"

extern "C" JNIEXPORT jint JNICALL
Java_com_example_post35_ndk_PageSizeManager_getSystemPageSize(
        JNIEnv* env,
        jobject thiz) {

    long pageSize = getpagesize();
    __android_log_print(ANDROID_LOG_INFO, LOG_TAG, "Page size from NDK: %ld bytes", pageSize);
    return (jint)pageSize;
}