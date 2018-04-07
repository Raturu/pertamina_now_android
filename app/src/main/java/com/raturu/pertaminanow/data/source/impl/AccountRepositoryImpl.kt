package com.raturu.pertaminanow.data.source.impl

import android.content.Context
import com.google.gson.Gson
import com.raturu.pertaminanow.data.model.Account
import com.raturu.pertaminanow.data.model.User
import com.raturu.pertaminanow.data.source.AccountRepository
import io.reactivex.Single

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class AccountRepositoryImpl(context: Context) : AccountRepository {
    private val sharedPreferences = context.getSharedPreferences("account", Context.MODE_PRIVATE)
    private val gson = Gson()

    override fun isLoggedIn(): Single<Boolean> {
        return Single.just(sharedPreferences.contains("account"))
    }

    override fun login(email: String, password: String): Single<Account> {
        //TODO call API
        saveAccount(Account(User("dummy", "dummy"), "dummy"))
        return Single.just(gson.fromJson(sharedPreferences.getString("account", ""), Account::class.java))
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
}