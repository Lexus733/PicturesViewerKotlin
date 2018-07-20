package com.example.dmitry.picturesviewerkotlin.presentation.pictureview

import com.arellomobile.mvp.MvpView


interface IPictureView : MvpView {
    fun showPicture(path: String)
}