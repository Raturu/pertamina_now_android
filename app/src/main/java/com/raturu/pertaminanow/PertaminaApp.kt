package com.raturu.pertaminanow

import android.app.Application
import com.facebook.stetho.Stetho
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class PertaminaApp : Application() {
    companion object {
        @Volatile
        private var INSTANCE: PertaminaApp? = null

        val instance: PertaminaApp
            get() {
                if (INSTANCE == null) {
                    synchronized(PertaminaApp::class.java) {
                        if (INSTANCE == null) {
                            throw RuntimeException()
                        }
                    }
                }

                return INSTANCE!!
            }
    }

    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        component = AppComponent(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
            Stetho.initializeWithDefaults(this)
        }
    }

    fun getComponent(): AppComponent {
        return component
    }
}
