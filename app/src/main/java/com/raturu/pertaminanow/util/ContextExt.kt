package com.raturu.pertaminanow.util

import android.content.Context
import android.support.v4.content.ContextCompat

/**
 * Created on : April 13, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
fun Context.getColorValue(id: Int): Int {
    return ContextCompat.getColor(this, id)
}