package com.raturu.pertaminanow.presenter

import com.raturu.pertaminanow.data.source.AccountRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created on : April 20, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class OtpCodeVerificationPresenter(private val view: View, private val accountRepository: AccountRepository) {

    fun verify(code: String) {
        view.showLoading()
        accountRepository.auth(code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.dismissLoading()
                    view.showEditProfilePage()
                }, {
                    it.printStackTrace()
                    view.dismissLoading()
                    it.message?.let { view.showErrorMessage(it) }
                })
    }

    interface View {
        fun showLoading()

        fun dismissLoading()

        fun showEditProfilePage()

        fun showErrorMessage(errorMessage: String)
    }
}