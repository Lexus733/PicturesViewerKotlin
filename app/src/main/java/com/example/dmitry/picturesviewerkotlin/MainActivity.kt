package com.example.dmitry.picturesviewerkotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.example.dmitry.picturesviewerkotlin.other.ScreenKeys
import com.example.dmitry.picturesviewerkotlin.presentation.generalscreen.GeneralScreenFragment
import com.example.dmitry.picturesviewerkotlin.presentation.pictureview.PictureViewFragment
import ru.terrakok.cicerone.android.SupportFragmentNavigator

class MainActivity : MvpAppCompatActivity() {
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
                finish()
            }

            override fun showSystemMessage(message: String?) {
                Toast.makeText(applicationContext, "Error!!", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainApplication.getRouter().navigateTo(ScreenKeys.GENERAL_SCREEN)
    }

    override fun onBackPressed() {
        MainApplication.getRouter().replaceScreen(ScreenKeys.GENERAL_SCREEN)
    }

    override fun onResume() {
        super.onResume()
        MainApplication.getNavigatorHolder().setNavigator(navigators)
    }

    override fun onPause() {
        super.onPause()
        MainApplication.getNavigatorHolder().removeNavigator()
    }
}