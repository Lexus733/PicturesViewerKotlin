package com.example.dmitry.picturesviewerkotlin

import android.app.Application
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        cicerone = Cicerone.create()
    }

    companion object {
        private var cicerone: Cicerone<Router>? = null

        fun getNavigatorHolder(): NavigatorHolder {
            return cicerone!!.navigatorHolder
        }

        fun getRouter(): Router {
            return cicerone!!.router
        }
    }

}
