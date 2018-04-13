package com.raturu.pertaminanow.presenter

import com.raturu.pertaminanow.data.source.AccountRepository

/**
 * Created on : April 13, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class SplashPresenter(private val view: View, private val accountRepository: AccountRepository) {

    fun start() {
        accountRepository.isLoggedIn()
                .subscribe({
                    when {
                        it -> view.showHomePage()
                        else -> view.showLoginPage()
                    }
                }, {
                    it.printStackTrace()
                })
    }

    interface View {

        fun showHomePage()

        fun showLoginPage()
    }
}