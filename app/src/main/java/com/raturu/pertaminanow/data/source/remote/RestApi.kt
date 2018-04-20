package com.raturu.pertaminanow.data.source.remote

import com.raturu.pertaminanow.data.source.remote.response.AuthResponse
import com.raturu.pertaminanow.data.source.remote.response.IsKtpVerifiedResponse
import com.raturu.pertaminanow.data.source.remote.response.RequestOtpCodeResponse
import com.raturu.pertaminanow.data.source.remote.response.VerifyKtpResponse
import io.reactivex.Single
import retrofit2.http.*


/**
 * Created on : April 09, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
interface RestApi {

    @Headers("x-api-key:CODEX@123")
    @FormUrlEncoded
    @POST("/pertamina-now/api/Collection/sendPhoneNumber/")
    fun requestOtpCode(
            @Field("no_tlp") phoneNumber: String
    ): Single<RequestOtpCodeResponse>

    @FormUrlEncoded
    @POST("/pertamina-now/api/Collection/verifySmsCode/")
    fun auth(
            @Header("x-api-key") key: String,
            @Field("request_id") requestCode: String,
            @Field("otp_code") otpCode: String
    ): Single<AuthResponse>

    @FormUrlEncoded
    @POST("/pertamina-now/api/Collection/inputKTP/")
    fun verifyKtp(
            @Header("x-api-key") token: String,
            @Field("ktp") ktpSerialNumber: String
    ): Single<VerifyKtpResponse>
    
    @GET("/pertamina-now/api/Collection/checkKTP")
    fun isKtpVerified(
            @Header("x-api-key") token: String
    ): Single<IsKtpVerifiedResponse>
}