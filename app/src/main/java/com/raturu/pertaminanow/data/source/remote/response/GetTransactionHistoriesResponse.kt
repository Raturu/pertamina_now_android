package com.raturu.pertaminanow.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created on : April 20, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
data class GetTransactionHistoriesResponse(
        @SerializedName("jenis_bbm") val jenisBbm: String,
        @SerializedName("waktu_transaksi") val waktuTransaksi: String,
        @SerializedName("total_pembelian") val totalPembelian: String,
        @SerializedName("harga") val harga: String,
        @SerializedName("total_pembayaran") val totalPembayaran: String,
        @SerializedName("nama_spbu") val namaSpbu: String,
        @SerializedName("alamat_spbu") val alamatSpbu: Double,
        @SerializedName("kota_spbu") val kotaSpbu: Double,
        @SerializedName("provinsi_spbu") val provinsiSpbu: String,
        @SerializedName("latitude") val latitude: String,
        @SerializedName("longitude") val longitude: String,
        @SerializedName("poin") val poin: String
)