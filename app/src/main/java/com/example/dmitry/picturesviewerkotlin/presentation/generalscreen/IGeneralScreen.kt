package com.example.dmitry.picturesviewerkotlin.presentation.generalscreen

import android.os.Bundle
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.dmitry.picturesviewerkotlin.domain.Image

interface IGeneralScreen : MvpView {
    fun setOnClickCreatePhoto(clickCreatePhoto: android.view.View.OnClickListener)
    fun showMessage(id: Int)

    @StateStrategyType(SkipStrategy::class)
    fun showDialog(image: Image)

    @StateStrategyType(SkipStrategy::class)
    fun goToFragment(pictureViewBundle: Bundle)
}