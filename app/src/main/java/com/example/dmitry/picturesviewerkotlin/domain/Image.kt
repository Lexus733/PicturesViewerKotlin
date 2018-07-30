package com.example.dmitry.picturesviewerkotlin.domain

import android.graphics.Bitmap
import java.io.File
import java.util.*

data class Image(var path: String?) {
    var image: Bitmap? = null

    val size: Float
        get() = File(path).length() / (1024 * 1024).toFloat()

    val date: Date
        get() = Date(File(path).lastModified())
}