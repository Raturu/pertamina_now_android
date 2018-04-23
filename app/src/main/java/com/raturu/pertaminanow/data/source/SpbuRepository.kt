package com.raturu.pertaminanow.data.source

import com.raturu.pertaminanow.data.model.Spbu
import io.reactivex.Single

/**
 * Created on : April 20, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
interface SpbuRepository {
    fun getNearbySpbu(latitude: Double, longitude: Double): Single<List<Spbu>>
}