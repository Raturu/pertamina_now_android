package com.raturu.pertaminanow.util

import java.text.NumberFormat
import java.util.*

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
private val rupiahFormat by lazy {
    val format = NumberFormat.getInstance(Locale.US)
    format.maximumFractionDigits = 2
    format.minimumFractionDigits = 2
    return@lazy format
}


fun Long.toRupiahFormat(): String {
    return "Rp. ${rupiahFormat.format(this)}"
}