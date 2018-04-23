package com.raturu.pertaminanow.ui.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.data.model.Voucher
import com.raturu.pertaminanow.ui.adapter.BaseAdapter
import com.raturu.pertaminanow.util.ColorUtil

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

    private val voucherImageView = itemView.findViewById<ImageView>(R.id.voucherImageView)
    private val voucherTitleTextView = itemView.findViewById<TextView>(R.id.voucherTitleTextView)
    private val pointTextView = itemView.findViewById<TextView>(R.id.pointTextView)

    override fun bind(data: Voucher) {
        voucherImageView.setBackgroundColor(ColorUtil.randomColor())
        Glide.with(itemView.context).load(data.imageUrl).into(voucherImageView)
        voucherTitleTextView.text = data.title
        pointTextView.text = "${data.points} pts"
    }

}