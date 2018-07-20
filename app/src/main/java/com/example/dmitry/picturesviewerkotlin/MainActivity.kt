package com.example.dmitry.picturesviewerkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.arellomobile.mvp.MvpAppCompatActivity
import com.example.dmitry.picturesviewerkotlin.presentation.generalscreen.GeneralScreenFragment

class MainActivity : MvpAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, GeneralScreenFragment()).commit()
    }
}
