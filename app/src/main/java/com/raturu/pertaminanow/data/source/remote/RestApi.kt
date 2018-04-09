package com.raturu.pertaminanow.data.source.remote

import com.raturu.pertaminanow.data.source.remote.response.LoginResponse
import com.raturu.pertaminanow.data.source.remote.response.RegisterResponse
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

    @Headers("x-api-key:CODEX@123")
    @FormUrlEncoded
    @POST("/pertamina-now/api/Collection/createUser/")
    fun register(
            @Field("nama") name: String,
            @Field("jenis_kelamin") gender: Int,
            @Field("tempat_lahir") placeOfBirth: String,
            @Field("tanggal_lahir") birthDate: String,
            @Field("email") email: String,
            @Field("no_tlp") phoneNumber: String,
            @Field("username") username: String,
            @Field("password") password: String,
            @Field("rule") rule: Int = 0
    ): Single<RegisterResponse>

    @Headers("x-api-key:CODEX@123")
    @FormUrlEncoded
    @POST("/pertamina-now/api/Collection/login/")
    fun login(
            @Field("username") username: String,
            @Field("password") password: String
    ): Single<LoginResponse>
}