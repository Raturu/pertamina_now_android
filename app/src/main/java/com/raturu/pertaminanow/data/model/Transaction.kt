package com.raturu.pertaminanow.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
@Parcelize
data class Transaction(
        val id: String,
        val createdAt: Date,
        val price: Long,
        val gasolineAmount: Double,
        val paidAmount: Long,
        val gasolineType: String,
        val spbu: Spbu,
        val point: Int
) : Parcelable