package com.example.dmitry.picturesviewerkotlin.presentation.mainactivity

import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.dmitry.picturesviewerkotlin.R

class MainActivity : MvpAppCompatActivity(), IMainActivity {
    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: MainActivityPresenter

    @ProvidePresenter(type = PresenterType.LOCAL)
    fun providedMainActivityPresenter(): MainActivityPresenter {
        return MainActivityPresenter(supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1)
            presenter.onBackPressed()
        else finish()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun showMessage(message: String?) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}