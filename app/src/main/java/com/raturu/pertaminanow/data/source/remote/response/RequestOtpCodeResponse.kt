package com.raturu.pertaminanow.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created on : April 13, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
data class RequestOtpCodeResponse(
        @SerializedName("key") val key: String,
        @SerializedName("request_id") val requestId: String
)