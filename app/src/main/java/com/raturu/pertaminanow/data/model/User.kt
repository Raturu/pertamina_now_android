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
data class User(
        val email: String,
        val ktp: String,
        val phoneNumber: String,
        val gender: Gender,
        val dateOfBirth: Date,
        val name: String,
        val avatar: String,
        val balance: Long,
        val point: Int
) : Parcelable