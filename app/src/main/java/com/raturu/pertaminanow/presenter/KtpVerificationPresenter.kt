package com.raturu.pertaminanow.presenter

import com.raturu.pertaminanow.data.source.AccountRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created on : April 21, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class KtpVerificationPresenter(private val view: View, private val accountRepository: AccountRepository) {

    fun verify(ktpSerialNumber: String) {
        accountRepository.verifyKtp(ktpSerialNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showKtpVerifiedDialog()
                }, {
                    it.printStackTrace()
                    it.message?.let { view.showErrorMessage(it) }
                })
    }

    interface View {
        fun showKtpVerifiedDialog()

        fun showErrorMessage(errorMessage: String)
    }
}