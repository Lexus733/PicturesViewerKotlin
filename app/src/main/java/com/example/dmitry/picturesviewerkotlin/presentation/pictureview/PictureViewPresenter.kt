package com.example.dmitry.picturesviewerkotlin.presentation.pictureview

import android.content.Intent
import com.example.dmitry.picturesviewerkotlin.other.IntentKeys


internal class PictureViewPresenter(view: IPictureView.View, intent: Intent) {
    init {
        view.showPicture(intent.getStringExtra(IntentKeys.PATH_TO_PHOTO))
    }
}
