package com.example.dmitry.picturesviewerkotlin.presentation.generalscreen

import com.example.dmitry.picturesviewerkotlin.domain.Image


interface IGeneralScreen {
    interface View {
        fun setOnClickCreatePhoto(clickCreatePhoto: android.view.View.OnClickListener)
        fun showMessage(id: Int)
        fun showDialog(image: Image)
    }
}