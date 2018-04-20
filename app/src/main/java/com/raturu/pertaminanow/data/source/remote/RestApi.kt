package com.raturu.pertaminanow.data.source.remote

import com.raturu.pertaminanow.data.source.remote.response.*
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

    @FormUrlEncoded
    @POST("/pertamina-now/api/Collection/updateProfil/")
    fun updateProfile(
            @Header("x-api-key") token: String,
            @Field("nama") name: String,
            @Field("jenis_kelamin") male: Int,
            @Field("tanggal_lahir") dateOfBirth: String,
            @Field("tempat_lahir") placeOfBirth: String,
            @Field("email") email: String,
            @Field("no_tlp") phoneNumber: String,
            @Field("username") username: String
    ): Single<UpdateProfileResponse>

    @FormUrlEncoded
    @POST("/pertamina-now/api/Collection/nearbySPBU/")
    fun getNearbySpbu(
            @Header("x-api-key") token: String,
            @Field("latitude") latitude: Double,
            @Field("longitude") longitude: Double
    ): Single<GetNearbySpbuResponse>

    @FormUrlEncoded
    @POST("/pertamina-now/api/Collection/promoSPBU/")
    fun getPromo(
            @Header("x-api-key") token: String,
            @Field("id_kategori") categoryId: Int? = null
    ): Single<GetPromoResponse>

    @GET("/pertamina-now/api/Collection/transaksi/")
    fun getTransactionHistories(
            @Header("x-api-key") token: String
    ): Single<GetTransactionHistoriesResponse>
}