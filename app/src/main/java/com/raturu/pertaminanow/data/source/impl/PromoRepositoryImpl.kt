package com.raturu.pertaminanow.data.source.impl

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.raturu.pertaminanow.data.model.Promo
import com.raturu.pertaminanow.data.source.AccountRepository
import com.raturu.pertaminanow.data.source.PromoRepository
import com.raturu.pertaminanow.data.source.remote.RestApi
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * Created on : April 20, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class PromoRepositoryImpl(context: Context, private val accountRepository: AccountRepository, private val restApi: RestApi) : PromoRepository {
    private val sharedPreferences = context.getSharedPreferences("promo", Context.MODE_PRIVATE)
    private val gson = Gson()

    override fun getPromoCategories(): Single<List<Promo.Category>> {
        refreshPromoCategoriesData()
        return Single.fromCallable { getCachedPromoCategories() }
                .flatMap { if (it.isEmpty()) getRemotePromoCategories() else Single.just(it) }
    }

    override fun getPromos(category: Promo.Category?): Single<List<Promo>> {
        refreshPromosData(category)
        return Single.fromCallable { getCachedPromos(category) }
                .flatMap { if (it.isEmpty()) getRemotePromos(category) else Single.just(it) }
    }

    private fun refreshPromoCategoriesData() {
        getRemotePromoCategories()
                .subscribeOn(Schedulers.io())
                .subscribe({}, {})
    }

    private fun refreshPromosData(category: Promo.Category?) {
        getRemotePromos(category)
                .subscribeOn(Schedulers.io())
                .subscribe({}, {})
    }

    private fun savePromoCategories(promoCategories: List<Promo.Category>) {
        sharedPreferences.edit().putString("promo_categories", gson.toJson(promoCategories)).apply()
        sharedPreferences.edit().putLong("promo_categories_timestamp", System.currentTimeMillis()).apply()
    }

    private fun savePromos(promos: List<Promo>, category: Promo.Category? = null) {
        sharedPreferences.edit().putString("promos_${category?.name}", gson.toJson(promos)).apply()
        sharedPreferences.edit().putLong("promos_${category?.name}_timestamp", System.currentTimeMillis()).apply()
    }

    private fun getCachedPromoCategories(): List<Promo.Category> {
        val rawJson = sharedPreferences.getString("promo_categories", "")
        val timestamp = sharedPreferences.getLong("promo_categories_timestamp", -1)

        if (rawJson.isBlank() || timestamp == -1L || System.currentTimeMillis() - timestamp >= 60000) {
            return arrayListOf()
        }

        return gson.fromJson(rawJson, object : TypeToken<List<Promo.Category>>() {}.type)
    }

    private fun getCachedPromos(category: Promo.Category?): List<Promo> {
        val rawJson = sharedPreferences.getString("promos_${category?.name}", "")
        val timestamp = sharedPreferences.getLong("promos_${category?.name}_timestamp", -1)

        if (rawJson.isBlank() || timestamp == -1L || System.currentTimeMillis() - timestamp >= 60000) {
            return arrayListOf()
        }

        return gson.fromJson(rawJson, object : TypeToken<List<Promo>>() {}.type)
    }

    private fun getRemotePromoCategories(): Single<List<Promo.Category>> {
        return accountRepository.getAccount()
                .flatMap { restApi.getPromoCategories(it.token) }
                .map { it.map { it.toPromoCategoryModel() } }
                .doOnSuccess { savePromoCategories(it) }
    }

    private fun getRemotePromos(category: Promo.Category?): Single<List<Promo>> {
        return accountRepository.getAccount()
                .flatMap { restApi.getPromos(it.token, category?.id) }
                .map { it.map { it.toPromoModel() } }
                .doOnSuccess { savePromos(it, category) }
    }
}