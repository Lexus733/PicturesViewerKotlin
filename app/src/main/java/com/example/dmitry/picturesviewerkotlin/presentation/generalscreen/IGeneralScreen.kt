package com.example.dmitry.picturesviewerkotlin.presentation.generalscreen

import android.view.View
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.dmitry.picturesviewerkotlin.domain.Image

interface IGeneralScreen : MvpView {
    fun showMessage(id: Int)

    @StateStrategyType(SkipStrategy::class)
    fun showDialog(image: Image)

    fun initView(adapter: GeneralScreenAdapter, listener: View.OnClickListener)
}