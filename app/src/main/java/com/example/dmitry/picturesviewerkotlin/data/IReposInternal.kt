package com.example.dmitry.picturesviewerkotlin.data

import android.content.Intent

interface IReposInternal {
    fun createPhoto(): Intent
    fun deleteFile(path: String)
    fun getData(): List<com.example.dmitry.picturesviewerkotlin.domain.Image>
}