package com.raturu.pertaminanow.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created on : April 13, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
@Parcelize
data class Voucher(
        val id: String,
        val title: String,
        val points: Int,
        val imageUrl: String
) : Parcelable