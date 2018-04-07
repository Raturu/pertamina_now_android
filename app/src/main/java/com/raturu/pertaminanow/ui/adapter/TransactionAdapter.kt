package com.raturu.pertaminanow.ui.adapter

import android.content.Context
import android.view.ViewGroup
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.data.model.Transaction
import com.raturu.pertaminanow.ui.adapter.viewholder.TransactionViewHolder

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class TransactionAdapter(context: Context) : BaseAdapter<Transaction, TransactionViewHolder>(context) {
    override fun getItemResourceLayout(viewType: Int): Int {
        return R.layout.item_transaction
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(getView(parent, viewType), itemClickListener, itemLongClickListener)
    }
}