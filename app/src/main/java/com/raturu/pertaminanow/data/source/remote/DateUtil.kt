package com.raturu.pertaminanow.data.source.remote

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created on : April 09, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

internal fun Date.formatDate(): String {
    return dateFormat.format(this)
}

internal fun String.toDate(): Date {
    return dateFormat.parse(this)
}