package com.example.dmitry.picturesviewerkotlin.presentation.pictureview

import android.os.Bundle
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dmitry.picturesviewerkotlin.other.IntentKeys

@InjectViewState
class PictureViewPresenter(bundle: Bundle):MvpPresenter<IPictureView>() {
    init {
        viewState.showPicture(bundle.getString(IntentKeys.PATH_TO_PHOTO))
    }
}
