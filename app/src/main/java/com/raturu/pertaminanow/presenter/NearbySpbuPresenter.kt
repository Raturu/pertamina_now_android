package com.raturu.pertaminanow.presenter

import com.raturu.pertaminanow.data.model.Spbu
import com.raturu.pertaminanow.data.source.SpbuRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created on : April 21, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class NearbySpbuPresenter(private val view: View, private val spbuRepository: SpbuRepository) {

    fun loadNearbySpbu() {
        spbuRepository.getNearbySpbu(0.0, 0.0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showNearbySpbu(it)
                }, {
                    it.printStackTrace()
                    it.message?.let { view.showErrorMessage(it) }
                })
    }

    interface View {
        fun showNearbySpbu(nearbySpbu: List<Spbu>)

        fun showErrorMessage(errorMessage: String)
    }
}