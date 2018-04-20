package com.raturu.pertaminanow.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.raturu.pertaminanow.data.model.Location
import com.raturu.pertaminanow.data.model.Spbu
import com.raturu.pertaminanow.data.model.Transaction
import com.raturu.pertaminanow.data.source.remote.toDate

/**
 * Created on : April 20, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
data class GetTransactionHistoriesResponse(
        @SerializedName("id") val id: String,
        @SerializedName("jenis_bbm") val jenisBbm: String,
        @SerializedName("waktu_transaksi") val waktuTransaksi: String,
        @SerializedName("total_pembelian") val totalPembelian: Double,
        @SerializedName("harga") val harga: Long,
        @SerializedName("total_pembayaran") val totalPembayaran: Long,
        @SerializedName("id_spbu") val idSpbu: String,
        @SerializedName("nama_spbu") val namaSpbu: String,
        @SerializedName("alamat_spbu") val alamatSpbu: String,
        @SerializedName("kota_spbu") val kotaSpbu: String,
        @SerializedName("provinsi_spbu") val provinsiSpbu: String,
        @SerializedName("latitude") val latitude: Double,
        @SerializedName("longitude") val longitude: Double,
        @SerializedName("poin") val poin: Int
) {
    fun toTransactionModel(): Transaction = Transaction(
            id,
            waktuTransaksi.toDate(),
            harga,
            totalPembelian,
            totalPembayaran,
            Spbu(idSpbu, namaSpbu, Location(alamatSpbu, kotaSpbu, provinsiSpbu, latitude, longitude)),
            poin
    )
}