package com.raturu.pertaminanow.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.raturu.pertaminanow.data.model.Location
import com.raturu.pertaminanow.data.model.Spbu

/**
 * Created on : April 20, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
data class GetNearbySpbuResponse(
        @SerializedName("id_spbu") val idSpbu: String,
        @SerializedName("no_spbu") val noSpbu: String,
        @SerializedName("nama_spbu") val namaSpbu: String,
        @SerializedName("alamat_spbu") val alamatSpbu: String,
        @SerializedName("kota_spbu") val kotaSpbu: String,
        @SerializedName("provinsi_spbu") val provinsiSpbu: String,
        @SerializedName("latitude") val latitude: Double,
        @SerializedName("longitude") val longitude: Double
) {
    fun toSpbuModel(): Spbu = Spbu(
            idSpbu,
            namaSpbu,
            Location(alamatSpbu, kotaSpbu, provinsiSpbu, latitude, longitude)
    )
}