package com.raturu.pertaminanow.presenter

import com.raturu.pertaminanow.data.model.Account
import com.raturu.pertaminanow.data.source.AccountRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created on : April 20, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class EditProfilePresenter(private val view: View, private val accountRepository: AccountRepository) {

    fun loadProfile() {
        accountRepository.getAccount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.dismissLoading()
                    view.showProfile(it)
                }, {
                    it.printStackTrace()
                    view.dismissLoading()
                    it.message?.let { view.showErrorMessage(it) }
                })
    }

    fun updateProfile(account: Account) {
        val fromOtpPage = account.user.name.isBlank()

        view.showLoading()
        accountRepository.updateAccount(account)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.dismissLoading()
                    when {
                        fromOtpPage -> view.showKtpVerificationPage()
                        else -> view.onProfileUpdated(it)
                    }
                }, {
                    it.printStackTrace()
                    view.dismissLoading()
                    it.message?.let { view.showErrorMessage(it) }
                })
    }

    interface View {
        fun showLoading()

        fun dismissLoading()

        fun showProfile(account: Account)

        fun showKtpVerificationPage()

        fun onProfileUpdated(account: Account)

        fun showErrorMessage(errorMessage: String)
    }
}