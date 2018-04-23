package com.raturu.pertaminanow.presenter

import com.raturu.pertaminanow.data.model.Spbu
import com.raturu.pertaminanow.data.source.AccountRepository
import com.raturu.pertaminanow.data.source.SpbuRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created on : April 21, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class NearbySpbuPresenter(private val view: View, private val accountRepository: AccountRepository, private val spbuRepository: SpbuRepository) {

    fun loadKtpVerifySpbuCode() {
        accountRepository.getKtpVerifySpbuCode()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showKtpVerifySpbuCode(it)
                }, {
                    it.printStackTrace()
                    it.message?.let { view.showErrorMessage(it) }
                })
    }

    fun loadNearbySpbu(latitude: Double, longitude: Double) {
        spbuRepository.getNearbySpbu(latitude, longitude)
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
        fun showKtpVerifySpbuCode(ktpVerifySpbuCode: String)

        fun showNearbySpbu(nearbySpbu: List<Spbu>)

        fun showErrorMessage(errorMessage: String)
    }
}