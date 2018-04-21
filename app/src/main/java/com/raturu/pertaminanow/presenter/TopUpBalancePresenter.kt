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
class TopUpBalancePresenter(private val view: View, private val accountRepository: AccountRepository) {

    fun loadBalance() {
        view.showLoading()
        accountRepository.getBalance()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.dismissLoading()
                    view.showBalance(it)
                }, {
                    it.printStackTrace()
                    view.dismissLoading()
                    it.message?.let { view.showErrorMessage(it) }
                })
    }

    fun topUpBalance(amount: Long) {
        view.showLoading()
        accountRepository.topUpBalance(amount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.dismissLoading()
                    view.onTopUpBalanceSuccess(it)
                }, {
                    it.printStackTrace()
                    view.dismissLoading()
                    it.message?.let { view.showErrorMessage(it) }
                })
    }

    interface View {
        fun showLoading()

        fun dismissLoading()

        fun showBalance(amount: Long)

        fun onTopUpBalanceSuccess(amount: Long)

        fun showErrorMessage(errorMessage: String)
    }
}