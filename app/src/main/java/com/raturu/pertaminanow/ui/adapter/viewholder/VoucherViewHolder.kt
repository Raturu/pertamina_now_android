package com.raturu.pertaminanow.ui.adapter.viewholder

import android.view.View
import com.raturu.pertaminanow.data.model.Voucher
import com.raturu.pertaminanow.ui.adapter.BaseAdapter

/**
 * Created on : April 13, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class VoucherViewHolder(itemView: View,
                        itemClickListener: BaseAdapter.OnItemClickListener?,
                        itemLongClickListener: BaseAdapter.OnItemLongClickListener?)
    : BaseViewHolder<Voucher>(itemView, itemClickListener, itemLongClickListener) {

    override fun bind(data: Voucher) {
        //TODO bind voucher data
    }

}