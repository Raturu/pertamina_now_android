package com.raturu.pertaminanow.data.source.impl

import android.content.Context
import com.google.gson.Gson
import com.raturu.pertaminanow.data.model.Account
import com.raturu.pertaminanow.data.model.Gender
import com.raturu.pertaminanow.data.source.AccountRepository
import com.raturu.pertaminanow.data.source.remote.RestApi
import com.raturu.pertaminanow.data.source.remote.formatDate
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

    override fun verifyKtp(ktpSerialNumber: String): Single<Unit> {
        return getAccount().flatMap { restApi.verifyKtp(it.token, ktpSerialNumber) }
                .map { Unit }
                .doOnSuccess {
                    val account = getAccount().blockingGet()
                    saveAccount(account.copy(user = account.user.copy(ktp = ktpSerialNumber)))
                }
    }

    override fun isKtpVerified(): Single<Boolean> {
        return getAccount().flatMap { restApi.isKtpVerified(it.token) }
                .map { it.status }
    }

    override fun getAccount(): Single<Account> {
        return Single.fromCallable {
            val rawJson = sharedPreferences.getString("account", "")
            if (rawJson.isBlank()) {
                throw IllegalStateException("You are not logged in!")
            }
            
            return@fromCallable gson.fromJson(rawJson, Account::class.java)
        }
    }

    override fun updateAccount(account: Account): Single<Account> {
        return restApi.updateProfile(
                account.token,
                account.user.name,
                if (account.user.gender == Gender.MALE) 1 else 0,
                account.user.dateOfBirth.formatDate(),
                account.user.email,
                account.user.phoneNumber
        )
                .map { account }
                .doOnSuccess { saveAccount(it) }
    }

    override fun getBalance(): Single<Long> {
        return getAccount().flatMap { restApi.getBalance(it.token) }
                .map { it.balance }
                .doOnSuccess {
                    val account = getAccount().blockingGet()
                    saveAccount(account.copy(user = account.user.copy(balance = it)))
                }
    }

    override fun topUpBalance(amount: Long): Single<Long> {
        return getAccount().flatMap { restApi.topUpBalance(it.token, amount) }
                .flatMap { getBalance() }
    }

    override fun getPoint(): Single<Int> {
        return getAccount().flatMap { restApi.getPoint(it.token) }
                .map { it.point }
                .doOnSuccess {
                    val account = getAccount().blockingGet()
                    saveAccount(account.copy(user = account.user.copy(point = it)))
                }
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