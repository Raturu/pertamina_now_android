package com.raturu.pertaminanow.util

import android.graphics.Color
import java.util.*

/**
 * Created on : April 13, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
private val random = Random()

object ColorUtil {
    fun randomColor(): Int {
        return Color.argb(100, random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }
}