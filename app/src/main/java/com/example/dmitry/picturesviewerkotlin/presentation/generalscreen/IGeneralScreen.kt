package com.example.dmitry.picturesviewerkotlin.presentation.generalscreen

import android.os.Bundle
import com.arellomobile.mvp.MvpView
import com.example.dmitry.picturesviewerkotlin.domain.Image


interface IGeneralScreen : MvpView {
    fun setOnClickCreatePhoto(clickCreatePhoto: android.view.View.OnClickListener)
    fun showMessage(id: Int)
    fun showDialog(image: Image)
    fun goToFragment(pictureViewBundle: Bundle)
}