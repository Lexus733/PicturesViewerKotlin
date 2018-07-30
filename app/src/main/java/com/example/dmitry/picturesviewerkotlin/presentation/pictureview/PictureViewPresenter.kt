package com.example.dmitry.picturesviewerkotlin.presentation.pictureview

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class PictureViewPresenter(string: String) : MvpPresenter<IPictureView>() {
    init {
        viewState.showPicture(string)
    }
}
