package com.raturu.pertaminanow.data.source.impl

import android.content.Context
import com.google.gson.Gson
import com.raturu.pertaminanow.data.model.Account
import com.raturu.pertaminanow.data.source.AccountRepository
import com.raturu.pertaminanow.data.source.remote.RestApi
import com.raturu.pertaminanow.data.source.remote.response.RequestOtpCodeResponse
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
        return Single.fromCallable { sharedPreferences.contains("account") }
    }

    override fun requestOtpCode(phoneNumber: String): Single<Unit> {
        return restApi.requestOtpCode(phoneNumber)
                .doOnSuccess { saveOtpRequest(it) }
                .map { Unit }
    }

    override fun auth(otpCode: String): Single<Account> {
        return Single.fromCallable { getRequestCode() }
                .flatMap { restApi.auth(it.key, it.requestId, otpCode) }
                .map { it.toAccountModel() }
                .doOnSuccess { saveAccount(it) }
    }

    override fun getAccount(): Single<Account> {
        return Single.fromCallable {
            gson.fromJson(sharedPreferences.getString("account", ""), Account::class.java)
        }
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

    private fun saveOtpRequest(otpCodeResponse: RequestOtpCodeResponse) {
        sharedPreferences.edit().putString("otp_request", gson.toJson(otpCodeResponse)).apply()
    }

    private fun getRequestCode(): RequestOtpCodeResponse {
        val rawJson = sharedPreferences.getString("otp_request", "")

        if (rawJson.isBlank()) {
            throw IllegalStateException("You don't have any request code!")
        }
        return gson.fromJson(rawJson, RequestOtpCodeResponse::class.java)
    }
}