package com.raturu.pertaminanow.presenter

import com.raturu.pertaminanow.data.model.Promo
import com.raturu.pertaminanow.data.source.PromoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created on : April 20, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class PromoPresenter(private val view: View, private val promoRepository: PromoRepository) {

    fun loadPromos(category: Promo.Category? = null) {
        view.showLoading()
        promoRepository.getPromos(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.dismissLoading()
                    view.showPromos(it)
                }, {
                    it.printStackTrace()
                    view.dismissLoading()
                    it.message?.let { view.showErrorMessage(it) }
                })
    }

    interface View {
        fun showLoading()

        fun dismissLoading()

        fun showPromos(promos: List<Promo>)

        fun showErrorMessage(errorMessage: String)
    }
}