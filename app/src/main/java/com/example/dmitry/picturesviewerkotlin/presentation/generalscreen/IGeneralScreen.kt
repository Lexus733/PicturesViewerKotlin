package com.example.dmitry.picturesviewerkotlin.presentation.generalscreen

import com.arellomobile.mvp.MvpView
import com.example.dmitry.picturesviewerkotlin.domain.Image
import com.example.dmitry.picturesviewerkotlin.presentation.pictureview.PictureViewFragment


interface IGeneralScreen : MvpView {
    fun setOnClickCreatePhoto(clickCreatePhoto: android.view.View.OnClickListener)
    fun showMessage(id: Int)
    fun showDialog(image: Image)
    fun goToFragment(pictureViewFragment: PictureViewFragment)
}