package com.raturu.pertaminanow.util

/**
 * Created on : April 13, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
object ImageUtil {
    fun getImage(id: Int, width: Int = 640, height: Int = 480): String {
        return "https://picsum.photos/$width/$height?image=$id"
    }
}