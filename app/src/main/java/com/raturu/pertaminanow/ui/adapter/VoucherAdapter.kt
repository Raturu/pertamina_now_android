package com.raturu.pertaminanow.ui.adapter

import android.content.Context
import android.view.ViewGroup
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.data.model.Voucher
import com.raturu.pertaminanow.ui.adapter.viewholder.VoucherViewHolder

/**
 * Created on : April 13, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class VoucherAdapter(context: Context) : BaseAdapter<Voucher, VoucherViewHolder>(context) {
    override fun getItemResourceLayout(viewType: Int): Int {
        return R.layout.item_voucher
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoucherViewHolder {
        return VoucherViewHolder(getView(parent, viewType), itemClickListener, itemLongClickListener)
    }

}