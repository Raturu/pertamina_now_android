package com.raturu.pertaminanow.presenter

import com.raturu.pertaminanow.data.source.AccountRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class LoginPresenter(private val view: View, private val accountRepository: AccountRepository) {

    fun auth(phoneNumber: String) {
        view.showLoading()
        accountRepository.requestOtpCode(phoneNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.dismissLoading()
                    view.showOtpCodeVerificationPage()
                }, {
                    it.printStackTrace()
                    view.dismissLoading()
                    it.message?.let { view.showErrorMessage(it) }
                })
    }

    interface View {
        fun showLoading()

        fun dismissLoading()

        fun showOtpCodeVerificationPage()

        fun showErrorMessage(errorMessage: String)
    }
}