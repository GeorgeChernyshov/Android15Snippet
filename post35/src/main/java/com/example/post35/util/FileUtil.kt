package com.example.post35.util

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import java.io.BufferedReader
import java.io.InputStreamReader

object FileUtil {

    fun saveTextFile(
        context: Context,
        fileName: String,
        content: String
    ): Uri? {
        val contentResolver = context.contentResolver
        val collection = MediaStore.Downloads.EXTERNAL_CONTENT_URI

        val details = ContentValues().apply {
            put(MediaStore.Downloads.DISPLAY_NAME, fileName)
            put(MediaStore.Downloads.MIME_TYPE, "text/plain")
            put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
            put(MediaStore.Downloads.IS_PENDING, 1)
        }

        var uri: Uri? = null
        try {
            uri = contentResolver.insert(collection, details)
            uri?.let {
                contentResolver.openOutputStream(it)?.use { outputStream ->
                    outputStream.write(content.toByteArray())
                }
                details.clear()
                details.put(MediaStore.Downloads.IS_PENDING, 0) // Mark as not pending
                contentResolver.update(
                    it,
                    details,
                    null,
                    null
                )

                return it
            }
        } catch (e: Exception) {
            uri?.let {
                contentResolver.delete(
                    it,
                    null,
                    null
                )
            }
        }

        return null
    }

    fun readTextFile(context: Context, fileName: String): String? {
        val contentResolver = context.contentResolver
        val collection = MediaStore.Downloads.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
            MediaStore.Downloads._ID,
            MediaStore.Downloads.DISPLAY_NAME,
            MediaStore.Downloads.MIME_TYPE
        )

        val selection = "${MediaStore.Downloads.DISPLAY_NAME} LIKE ?"
        val selectionArgs = arrayOf(fileName)
        val sortOrder = "${MediaStore.Downloads.DATE_ADDED} DESC"

        contentResolver.query(
            collection,
            projection,
            selection,
            selectionArgs,
            sortOrder
        )?.use { cursor ->
            if (cursor.moveToFirst()) {
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Downloads._ID)
                val id = cursor.getLong(idColumn)
                val fileUri = Uri.withAppendedPath(collection, id.toString())

                context.contentResolver.openInputStream(fileUri)?.use { inputStream ->
                    BufferedReader(InputStreamReader(inputStream)).use { reader ->

                        return reader.readText()
                    }
                }
            }
        }

        return null
    }
}