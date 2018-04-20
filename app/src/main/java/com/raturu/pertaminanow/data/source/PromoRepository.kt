package com.raturu.pertaminanow.data.source

import com.raturu.pertaminanow.data.model.Promo
import io.reactivex.Single

/**
 * Created on : April 20, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
interface PromoRepository {
    fun getPromoCategories(): Single<List<Promo.Category>>

    fun getPromos(category: Promo.Category? = null): Single<List<Promo>>
}