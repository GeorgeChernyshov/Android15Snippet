package com.example.post35.ndk

class PageSizeManager {
    external fun getSystemPageSize(): Int

    init {
        System.loadLibrary("pagesize")
    }
}