package com.raturu.pertaminanow.data.source

import com.raturu.pertaminanow.data.model.Transaction
import io.reactivex.Single

/**
 * Created on : April 20, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
interface TransactionRepository {
    fun getTransactionHistories(): Single<List<Transaction>>
}