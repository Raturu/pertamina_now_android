package com.raturu.pertaminanow.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.raturu.pertaminanow.data.model.Account
import com.raturu.pertaminanow.data.model.Gender
import com.raturu.pertaminanow.data.model.User
import com.raturu.pertaminanow.data.source.remote.toDate

/**
 * Created on : April 13, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
data class AuthResponse(
        @SerializedName("nama") val nama: String,
        @SerializedName("ktp") val ktp: String,
        @SerializedName("jenis_kelamin") val jenisKelamin: String,
        @SerializedName("tanggal_lahir") val tanggalLahir: String,
        @SerializedName("tempat_lahir") val tempatLahir: String,
        @SerializedName("email") val email: String,
        @SerializedName("no_tlp") val noTlp: String,
        @SerializedName("saldo") val saldo: Long,
        @SerializedName("poin") val poin: Int,
        @SerializedName("API_key") val apiKey: String
) {
    fun toAccountModel(): Account = Account(
            User(
                    email,
                    ktp,
                    noTlp,
                    if (jenisKelamin == "0") Gender.FEMALE else Gender.MALE,
                    tanggalLahir.toDate(),
                    nama,
                    "",
                    saldo,
                    poin
            ),
            apiKey
    )
}