package com.raturu.pertaminanow

import android.content.Context
import com.raturu.pertaminanow.data.source.impl.AccountRepositoryImpl
import com.raturu.pertaminanow.data.source.impl.PromoRepositoryImpl
import com.raturu.pertaminanow.data.source.impl.SpbuRepositoryImpl
import com.raturu.pertaminanow.data.source.impl.TransactionRepositoryImpl
import com.raturu.pertaminanow.data.source.remote.HttpClientFactory
import com.raturu.pertaminanow.data.source.remote.RestApiFactory

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class AppComponent(context: Context) {
    private val restApi = RestApiFactory.makeRestApi("http://159.65.139.83", HttpClientFactory.makeOkHttpClient(BuildConfig.DEBUG))
    val accountRepository = AccountRepositoryImpl(context, restApi)
    val spbuRepository = SpbuRepositoryImpl(context, accountRepository, restApi)
    val promoRepository = PromoRepositoryImpl(context, accountRepository, restApi)
    val transactionRepository = TransactionRepositoryImpl(context, accountRepository, restApi)
}