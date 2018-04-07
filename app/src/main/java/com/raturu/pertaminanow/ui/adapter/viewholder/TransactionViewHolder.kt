package com.raturu.pertaminanow.ui.adapter.viewholder

import android.view.View
import com.raturu.pertaminanow.data.model.Transaction
import com.raturu.pertaminanow.ui.adapter.BaseAdapter

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class TransactionViewHolder(itemView: View,
                            itemClickListener: BaseAdapter.OnItemClickListener?,
                            itemLongClickListener: BaseAdapter.OnItemLongClickListener?)
    : BaseViewHolder<Transaction>(itemView, itemClickListener, itemLongClickListener) {

    override fun bind(data: Transaction) {

    }
}