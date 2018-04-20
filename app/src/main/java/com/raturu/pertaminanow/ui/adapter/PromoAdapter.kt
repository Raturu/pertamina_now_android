package com.raturu.pertaminanow.ui.adapter

import android.content.Context
import android.view.ViewGroup
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.data.model.Promo
import com.raturu.pertaminanow.ui.adapter.viewholder.PromoViewHolder

/**
 * Created on : April 13, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class PromoAdapter(context: Context) : BaseAdapter<Promo, PromoViewHolder>(context) {

    override fun getItemResourceLayout(viewType: Int): Int = R.layout.item_promo

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoViewHolder {
        return PromoViewHolder(getView(parent, viewType), itemClickListener, itemLongClickListener)
    }
}