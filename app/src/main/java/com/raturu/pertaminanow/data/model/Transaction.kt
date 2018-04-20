package com.raturu.pertaminanow.data.model

import java.util.*

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
data class Transaction(
        val id: String,
        val createdAt: Date,
        val price: Long,
        val gasolineAmount: Double,
        val paidAmount: Long,
        val spbu: Spbu,
        val point: Int
)