package com.raturu.pertaminanow.util

import java.text.NumberFormat
import java.util.*

/**
 * Created on : April 22, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
private val pointFormat by lazy {
    val format = NumberFormat.getInstance(Locale.GERMAN)
    return@lazy format
}

fun Int.toPointFormat(): String = "Rp. ${pointFormat.format(this)}"