package com.raturu.pertaminanow.ui.adapter.viewholder

import android.view.View
import android.widget.TextView
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.data.model.Transaction
import com.raturu.pertaminanow.ui.adapter.BaseAdapter
import com.raturu.pertaminanow.util.toFullDateFormat
import com.raturu.pertaminanow.util.toRupiahFormat

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

    private val paidAmount = itemView.findViewById<TextView>(R.id.paidAmount)
    private val gasolineAmount = itemView.findViewById<TextView>(R.id.gasolineAmount)
    private val createdAt = itemView.findViewById<TextView>(R.id.createdAt)

    override fun bind(data: Transaction) {
        paidAmount.text = data.paidAmount.toRupiahFormat()
        gasolineAmount.text = "${data.gasolineAmount} Litre"
        createdAt.text = data.createdAt.toFullDateFormat()
    }
}