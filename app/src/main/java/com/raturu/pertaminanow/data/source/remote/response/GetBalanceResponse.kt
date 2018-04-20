package com.raturu.pertaminanow.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created on : April 20, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
data class GetBalanceResponse(
        @SerializedName("status") val status: Boolean,
        @SerializedName("balance") val balance: Long
)