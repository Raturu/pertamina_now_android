package com.raturu.pertaminanow.presenter

import com.raturu.pertaminanow.data.source.AccountRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created on : April 22, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class VouchersPresenter(private val view: View, private val accountRepository: AccountRepository) {

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

    interface View {
        fun showPoint(point: Int)

        fun showErrorMessage(errorMessage: String)
    }
}