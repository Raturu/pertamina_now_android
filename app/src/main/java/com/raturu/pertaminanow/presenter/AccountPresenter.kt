package com.raturu.pertaminanow.presenter

import com.raturu.pertaminanow.data.source.AccountRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created on : April 09, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class AccountPresenter(private val view: View, private val accountRepository: AccountRepository) {

    fun loadBalance() {
        accountRepository.getBalance()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showBalance(it)
                }, {
                    it.printStackTrace()
                    it.message?.let { view.showErrorMessage(it) }
                })
    }

    fun loadPoint() {
        accountRepository.getPoint()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showPoint(it)
                }, {
                    it.printStackTrace()
                    it.message?.let { view.showErrorMessage(it) }
                })
    }

    fun logout() {
        accountRepository.logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view.showLoginPage() }, {})
    }

    interface View {
        fun showBalance(amount: Long)

        fun showPoint(point: Int)

        fun showLoginPage()

        fun showErrorMessage(errorMessage: String)
    }
}