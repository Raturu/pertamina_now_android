package com.raturu.pertaminanow.util

import android.content.Context
import android.location.Location
import com.raturu.pertaminanow.PertaminaApp

/**
 * Created on : April 22, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
object LocationUtil {
    private val sharedPreferences = PertaminaApp.instance.getSharedPreferences("location", Context.MODE_PRIVATE)

    fun saveLastLocation(location: Location) {
        sharedPreferences.edit().putFloat("latitude", location.latitude.toFloat()).apply()
        sharedPreferences.edit().putFloat("longitude", location.longitude.toFloat()).apply()
    }

    fun getLastLocation(): Location {
        val latitude = sharedPreferences.getFloat("latitude", 0.0F).toDouble()
        val longitude = sharedPreferences.getFloat("longitude", 0.0F).toDouble()
        val location = Location("")
        location.latitude = latitude
        location.longitude = longitude
        return location
    }
}