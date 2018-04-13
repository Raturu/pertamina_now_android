package com.raturu.pertaminanow.ui.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.data.model.Promo
import com.raturu.pertaminanow.ui.adapter.BaseAdapter
import com.raturu.pertaminanow.util.ColorUtil
import com.raturu.pertaminanow.util.toSimpleMonthFormat

/**
 * Created on : April 13, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class PromoViewHolder(itemView: View,
                      itemClickListener: BaseAdapter.OnItemClickListener?,
                      itemLongClickListener: BaseAdapter.OnItemLongClickListener?)
    : BaseViewHolder<Promo>(itemView, itemClickListener, itemLongClickListener) {

    private val promoImageView = itemView.findViewById<ImageView>(R.id.promoImageView)
    private val promoTitleTextView = itemView.findViewById<TextView>(R.id.promoTitleTextView)
    private val periodTextView = itemView.findViewById<TextView>(R.id.promoPeriodTextView)

    override fun bind(data: Promo) {
        promoImageView.setBackgroundColor(ColorUtil.randomColor())
        Glide.with(itemView.context).load(data.imageUrl).into(promoImageView)
        promoTitleTextView.text = data.title
        periodTextView.text = "Promo period: ${data.startDate.toSimpleMonthFormat()} - ${data.endDate.toSimpleMonthFormat()}"
    }

}