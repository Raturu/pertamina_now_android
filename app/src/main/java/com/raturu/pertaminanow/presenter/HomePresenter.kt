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
class HomePresenter(private val view: View, private val promoRepository: PromoRepository) {

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

        fun showPromoFragments(promoCategories: List<Promo.Category>)

        fun showErrorMessage(errorMessage: String)
    }
}