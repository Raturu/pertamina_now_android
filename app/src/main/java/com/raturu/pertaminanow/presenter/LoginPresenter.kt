package com.raturu.pertaminanow.presenter

import com.raturu.pertaminanow.data.source.AccountRepository

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class LoginPresenter(private val view: LoginPresenter.View, private val accountRepository: AccountRepository) {

    fun auth(phoneNumber: String) {
        view.showLoading()
        //TODO implement OTP
    }

    interface View {
        fun showLoading()

        fun dismissLoading()

        fun showHomePage()

        fun showErrorMessage(errorMessage: String)
    }
}