package com.raturu.pertaminanow.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.raturu.pertaminanow.data.model.Promo

/**
 * Created on : April 13, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
data class GetPromoCategoriesResponse(
        @SerializedName("id") val id: String,
        @SerializedName("nama") val nama: String
) {
    fun toPromoCategoryModel(): Promo.Category = Promo.Category(id, nama)
}