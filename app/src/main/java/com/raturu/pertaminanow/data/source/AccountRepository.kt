package com.raturu.pertaminanow.data.source

import com.raturu.pertaminanow.data.model.Account
import io.reactivex.Single

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
interface AccountRepository {
    fun isLoggedIn(): Single<Boolean>

    fun requestOtpCode(phoneNumber: String): Single<Unit>

    fun auth(otpCode: String): Single<Account>

    fun getAccount(): Single<Account>

    fun updateAccount(account: Account): Single<Account>

    fun logout(): Single<Unit>
}