package com.raturu.pertaminanow.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Created on : April 13, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
@Parcelize
data class Promo(
        val id: String,
        val category: Category,
        val title: String,
        val description: String,
        val imageUrl: String,
        val startDate: Date,
        val endDate: Date,
        val spbu: Spbu,
        val point: Int
) : Parcelable {
    @Parcelize
    data class Category(
            val id: String,
            val name: String
    ) : Parcelable
}