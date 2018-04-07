package com.raturu.pertaminanow.presenter

import com.raturu.pertaminanow.data.source.AccountRepository

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class LoginPresenter(private val view: LoginPresenter.View, private val accountRepository: AccountRepository) {

    fun start() {
        accountRepository.isLoggedIn()
                .subscribe({
                    if (it) {
                        view.showHomePage()
                    }
                }, {
                    it.printStackTrace()
                })
    }

    fun login(email: String, password: String) {
        view.showLoading()
        accountRepository.login(email, password)
                .subscribe({
                    view.dismissLoading()
                    view.showHomePage()
                }, {
                    it.printStackTrace()
                    it.message?.let { view.showErrorMessage(it) }
                })
    }

    interface View {
        fun showLoading()

        fun dismissLoading()

        fun showHomePage()

        fun showErrorMessage(errorMessage: String)
    }
}