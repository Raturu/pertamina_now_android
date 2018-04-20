package com.raturu.pertaminanow.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.raturu.pertaminanow.PertaminaApp
import com.raturu.pertaminanow.presenter.SplashPresenter

/**
 * Created on : April 13, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class SplashActivity : Activity(), SplashPresenter.View {

    private lateinit var splashPresenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashPresenter = SplashPresenter(this, PertaminaApp.instance.getComponent().accountRepository)
        splashPresenter.start()
    }

    override fun showHomePage() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    override fun showLoginPage() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun showEditProfilePage() {
        startActivity(Intent(this, EditProfileActivity::class.java))
        finish()
    }

    override fun showKtpVerificationPage() {
        startActivity(Intent(this, KtpVerificationActivity::class.java))
        finish()
    }
}