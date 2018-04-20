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
                        it -> checkAccountState()
                        else -> view.showLoginPage()
                    }
                }, {
                    it.printStackTrace()
                })
    }

    private fun checkAccountState() {
        accountRepository.getAccount()
                .subscribe({
                    when {
                        it.user.name.isBlank() -> view.showEditProfilePage()
                        it.user.ktp.isBlank() -> view.showKtpVerificationPage()
                        else -> view.showHomePage()
                    }
                }, {
                    it.printStackTrace()
                })
    }

    interface View {

        fun showHomePage()

        fun showLoginPage()

        fun showEditProfilePage()

        fun showKtpVerificationPage()
    }
}