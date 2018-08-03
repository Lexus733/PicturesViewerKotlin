package com.example.dmitry.picturesviewerkotlin.presentation.mainactivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dmitry.picturesviewerkotlin.MainApplication
import com.example.dmitry.picturesviewerkotlin.R
import com.example.dmitry.picturesviewerkotlin.other.ScreenKeys
import com.example.dmitry.picturesviewerkotlin.presentation.generalscreen.GeneralScreenFragment
import com.example.dmitry.picturesviewerkotlin.presentation.pictureview.PictureViewFragment
import ru.terrakok.cicerone.android.SupportFragmentNavigator

@InjectViewState
class MainActivityPresenter(private val supportFragmentManager: FragmentManager) : MvpPresenter<IMainActivity>() {
    private val navigators: SupportFragmentNavigator
        get() = object : SupportFragmentNavigator(supportFragmentManager, R.id.container) {
            override fun createFragment(screenKey: String?, data: Any?): Fragment {
                return when (screenKey) {
                    ScreenKeys.GENERAL_SCREEN -> GeneralScreenFragment()
                    ScreenKeys.PICTURE_VIEW -> PictureViewFragment.newInstance(data as Bundle)
                    else -> throw RuntimeException()
                }
            }

            override fun exit() {
                viewState.finish()
            }

            override fun showSystemMessage(message: String?) {
                viewState.showMessage("Error!!! Can't access to fragment")
            }
        }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        MainApplication.getRouter().navigateTo(ScreenKeys.GENERAL_SCREEN)
    }

    fun onBackPressed() = MainApplication.getRouter().exit()

    fun onResume() = MainApplication.getNavigatorHolder().setNavigator(navigators)

    fun onPause() = MainApplication.getNavigatorHolder().removeNavigator()

}