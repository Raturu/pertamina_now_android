package com.raturu.pertaminanow

import android.content.Context
import com.raturu.pertaminanow.data.source.impl.AccountRepositoryImpl

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class AppComponent(context: Context) {
    val accountRepository = AccountRepositoryImpl(context)
}