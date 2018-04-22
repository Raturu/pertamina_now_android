package com.raturu.pertaminanow.presenter

import com.raturu.pertaminanow.data.model.Promo
import com.raturu.pertaminanow.data.source.AccountRepository
import com.raturu.pertaminanow.data.source.PromoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created on : April 20, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class HomePresenter(private val view: View, private val accountRepository: AccountRepository, private val promoRepository: PromoRepository) {

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

    fun loadPromoCategories() {
        view.showLoading()
        promoRepository.getPromoCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.dismissLoading()
                    view.showPromoFragments(it)
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

        fun showPoint(point: Int)

        fun showPromoFragments(promoCategories: List<Promo.Category>)

        fun showErrorMessage(errorMessage: String)
    }
}