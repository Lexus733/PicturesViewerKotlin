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
            return requireNotNull(cicerone,{"Parameter 'cicerone' is missing!"}).navigatorHolder
        }

        fun getRouter(): Router {
            return requireNotNull(cicerone,{"Parameter 'cicerone' is missing!"}).router
        }
    }

}
