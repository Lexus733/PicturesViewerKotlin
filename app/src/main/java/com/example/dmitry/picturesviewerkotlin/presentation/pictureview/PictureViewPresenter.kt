package com.example.dmitry.picturesviewerkotlin.presentation.pictureview

import android.os.Bundle
import com.example.dmitry.picturesviewerkotlin.other.IntentKeys


internal class PictureViewPresenter(view: IPictureView.View, bundle: Bundle) {
    init {
        view.showPicture(bundle.getString(IntentKeys.PATH_TO_PHOTO))
    }
}
