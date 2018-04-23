package com.raturu.pertaminanow.presenter

import com.raturu.pertaminanow.data.model.Transaction
import com.raturu.pertaminanow.data.source.TransactionRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created on : April 20, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class TransactionPresenter(private val view: View, private val transactionRepository: TransactionRepository) {

    fun loadTransactionHistories() {
        view.showLoading()
        transactionRepository.getTransactionHistories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.dismissLoading()
                    view.showTransactionHistories(it)
                }, {
                    it.printStackTrace()
                    view.dismissLoading()
                    it.message?.let { view.showErrorMessage(it) }
                })
    }

    interface View {
        fun showLoading()

        fun dismissLoading()

        fun showTransactionHistories(transactionHistories: List<Transaction>)

        fun showErrorMessage(errorMessage: String)
    }
}