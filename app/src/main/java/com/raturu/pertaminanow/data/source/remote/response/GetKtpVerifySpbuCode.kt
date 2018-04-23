package com.raturu.pertaminanow.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created on : April 21, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
data class GetKtpVerifySpbuCode(
        @SerializedName("status") val status: Boolean,
        @SerializedName("code") val code: String
)