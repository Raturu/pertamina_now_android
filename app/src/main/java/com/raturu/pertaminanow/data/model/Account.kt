package com.raturu.pertaminanow.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
@Parcelize
data class Account(
        val user: User,
        val token: String
) : Parcelable