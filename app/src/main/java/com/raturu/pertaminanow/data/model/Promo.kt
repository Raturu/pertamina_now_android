package com.raturu.pertaminanow.data.model

import java.util.*

/**
 * Created on : April 13, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
data class Promo(
        val id: String,
        val title: String,
        val description: String,
        val imageUrl: String,
        val startDate: Date,
        val endDate: Date
)