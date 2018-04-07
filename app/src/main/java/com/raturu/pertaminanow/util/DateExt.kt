package com.raturu.pertaminanow.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
private val fullDateFormat = SimpleDateFormat("dd MMMM YYYY", Locale.US)

fun Date.toFullDateFormat(): String {
    return fullDateFormat.format(this)
}