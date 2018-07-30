package com.example.dmitry.picturesviewerkotlin.data

import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import com.example.dmitry.picturesviewerkotlin.domain.Image
import java.io.File
import java.util.*

@Suppress("NAME_SHADOWING")
class ReposInternal : IReposInternal {
    override fun createPhoto(): Intent {
        val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val imageUri = Uri.fromFile(File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "pv_" + System.currentTimeMillis().toString() + ".jpg"))
        i.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        return i
    }

    override fun deleteFile(path: String) {
        File(path).delete()
    }

    override fun getData(): ArrayList<Image> {
        val images = ArrayList<Image>()
        val files = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                .toString())
                .listFiles()
        for (file in files) {
            val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), file.name)
            if (!file.isDirectory && !file.isHidden) {
                images.add(Image(file.absolutePath))
            }
        }
        return images
    }
}