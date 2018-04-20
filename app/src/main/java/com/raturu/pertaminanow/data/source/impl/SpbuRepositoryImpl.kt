package com.raturu.pertaminanow.data.source.impl

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.raturu.pertaminanow.data.model.Spbu
import com.raturu.pertaminanow.data.source.AccountRepository
import com.raturu.pertaminanow.data.source.SpbuRepository
import com.raturu.pertaminanow.data.source.remote.RestApi
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * Created on : April 20, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class SpbuRepositoryImpl(context: Context, private val accountRepository: AccountRepository, private val restApi: RestApi) : SpbuRepository {
    private val sharedPreferences = context.getSharedPreferences("spbu", Context.MODE_PRIVATE)
    private val gson = Gson()

    override fun getNearbySpbu(latitude: Double, longitude: Double): Single<List<Spbu>> {
        refreshNearbySpbuData(latitude, longitude)
        return Single.fromCallable { getCachedNearbySpbu() }
                .flatMap { if (it.isEmpty()) getRemoteNearbySpbu(latitude, longitude) else Single.just(it) }
    }

    private fun refreshNearbySpbuData(latitude: Double, longitude: Double) {
        getRemoteNearbySpbu(latitude, longitude)
                .subscribeOn(Schedulers.io())
                .subscribe({}, {})
    }

    private fun saveNearbySpbu(nearbySpbu: List<Spbu>) {
        sharedPreferences.edit().putString("nearby_spbu", gson.toJson(nearbySpbu)).apply()
        sharedPreferences.edit().putLong("nearby_spbu_timestamp", System.currentTimeMillis()).apply()
    }

    private fun getCachedNearbySpbu(): List<Spbu> {
        val rawJson = sharedPreferences.getString("nearby_spbu", "")
        val timestamp = sharedPreferences.getLong("nearby_spbu_timestamp", -1)

        if (rawJson.isBlank() || timestamp == -1L || System.currentTimeMillis() - timestamp >= 60000) {
            return arrayListOf()
        }

        return gson.fromJson(rawJson, object : TypeToken<List<Spbu>>() {}.type)
    }

    private fun getRemoteNearbySpbu(latitude: Double, longitude: Double): Single<List<Spbu>> {
        return accountRepository.getAccount()
                .flatMap { restApi.getNearbySpbu(it.token, latitude, longitude) }
                .map { it.map { it.toSpbuModel() } }
                .doOnSuccess { saveNearbySpbu(it) }
    }
}