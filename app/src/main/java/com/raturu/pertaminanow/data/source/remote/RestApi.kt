package com.raturu.pertaminanow.data.source.remote

import com.raturu.pertaminanow.data.source.remote.response.AuthResponse
import com.raturu.pertaminanow.data.source.remote.response.RequestOtpCodeResponse
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST


/**
 * Created on : April 09, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
interface RestApi {

    //TODO change end point
    @Headers("x-api-key:CODEX@123")
    @FormUrlEncoded
    @POST("/pertamina-now/api/Collection/login/")
    fun requestOtpCode(
            @Field("phone_number") phoneNumber: String
    ): Single<RequestOtpCodeResponse>

    //TODO change end point
    @Headers("x-api-key:CODEX@123")
    @FormUrlEncoded
    @POST("/pertamina-now/api/Collection/login/")
    fun auth(
            @Field("request_code") requestCode: String,
            @Field("otp_code") otpCode: String
    ): Single<AuthResponse>
}