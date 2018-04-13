package com.raturu.pertaminanow.data.source.impl

import android.content.Context
import com.google.gson.Gson
import com.raturu.pertaminanow.data.model.Account
import com.raturu.pertaminanow.data.source.AccountRepository
import com.raturu.pertaminanow.data.source.remote.RestApi
import io.reactivex.Single

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class AccountRepositoryImpl(context: Context, private val restApi: RestApi) : AccountRepository {
    private val sharedPreferences = context.getSharedPreferences("account", Context.MODE_PRIVATE)
    private val gson = Gson()

    override fun isLoggedIn(): Single<Boolean> {
        return Single.just(sharedPreferences.contains("account"))
    }

    override fun requestOtpCode(phoneNumber: String): Single<Unit> {
        return restApi.requestOtpCode(phoneNumber)
                .doOnSuccess { saveRequestCode(it.requestCode) }
                .map { Unit }
    }

    override fun auth(otpCode: String): Single<Account> {
        return Single.fromCallable { getRequestCode() }
                .flatMap { restApi.auth(it, otpCode) }
                .map { it.toAccountModel() }
                .doOnSuccess { saveAccount(it) }
    }

    override fun getAccount(): Single<Account> {
        //TODO call API
        return Single.just(gson.fromJson(sharedPreferences.getString("account", ""), Account::class.java))
    }

    override fun updateAccount(account: Account): Single<Account> {
        //TODO call API
        saveAccount(account)
        return getAccount()
    }

    override fun logout(): Single<Unit> {
        sharedPreferences.edit().clear().apply()
        return Single.just(Unit)
    }

    private fun saveAccount(account: Account) {
        sharedPreferences.edit().putString("account", gson.toJson(account)).apply()
    }

    private fun saveRequestCode(requestCode: String) {
        sharedPreferences.edit().putString("request_code", requestCode).apply()
    }

    private fun getRequestCode(): String {
        val requestCode = sharedPreferences.getString("request_code", "")

        if (requestCode.isBlank()) {
            throw IllegalStateException("You don't have any request code!")
        }
        return requestCode
    }
}