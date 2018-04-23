package com.raturu.pertaminanow.data.source.impl

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.raturu.pertaminanow.data.model.Transaction
import com.raturu.pertaminanow.data.source.AccountRepository
import com.raturu.pertaminanow.data.source.TransactionRepository
import com.raturu.pertaminanow.data.source.remote.RestApi
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * Created on : April 20, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class TransactionRepositoryImpl(context: Context, private val accountRepository: AccountRepository, private val restApi: RestApi) : TransactionRepository {
    private val sharedPreferences = context.getSharedPreferences("transaction", Context.MODE_PRIVATE)
    private val gson = Gson()

    override fun getTransactionHistories(): Single<List<Transaction>> {
        refreshTransactionHistories()
        return Single.fromCallable { getCachedTransactionHistories() }
                .flatMap { if (it.isEmpty()) getRemoteTransactionHistories() else Single.just(it) }
    }

    private fun refreshTransactionHistories() {
        getRemoteTransactionHistories()
                .subscribeOn(Schedulers.io())
                .subscribe({}, {})
    }

    private fun saveTransactionHistories(transactionHistories: List<Transaction>) {
        sharedPreferences.edit().putString("transaction_histories", gson.toJson(transactionHistories)).apply()
        sharedPreferences.edit().putLong("transaction_histories_timestamp", System.currentTimeMillis()).apply()
    }

    private fun getCachedTransactionHistories(): List<Transaction> {
        val rawJson = sharedPreferences.getString("transaction_histories", "")
        val timestamp = sharedPreferences.getLong("transaction_histories_timestamp", -1)

        if (rawJson.isBlank() || timestamp == -1L || System.currentTimeMillis() - timestamp >= 60000) {
            return arrayListOf()
        }

        return gson.fromJson(rawJson, object : TypeToken<List<Transaction>>() {}.type)
    }

    private fun getRemoteTransactionHistories(): Single<List<Transaction>> {
        return accountRepository.getAccount()
                .flatMap { restApi.getTransactionHistories(it.token) }
                .map { it.map { it.toTransactionModel() } }
                .doOnSuccess { saveTransactionHistories(it) }
    }
}