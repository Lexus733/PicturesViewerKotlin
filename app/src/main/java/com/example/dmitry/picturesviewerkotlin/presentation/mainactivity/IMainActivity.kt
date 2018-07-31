package com.example.dmitry.picturesviewerkotlin.presentation.mainactivity

import com.arellomobile.mvp.MvpView

interface IMainActivity : MvpView {
    fun finish()
    fun showMessage(message: String?)
}