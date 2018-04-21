package com.raturu.pertaminanow.ui.adapter

import android.content.Context
import android.view.ViewGroup
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.data.model.Spbu
import com.raturu.pertaminanow.ui.adapter.viewholder.NearbySpbuViewHolder

/**
 * Created on : April 21, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class NearbySpbuAdapter(context: Context) : BaseAdapter<Spbu, NearbySpbuViewHolder>(context) {

    override fun getItemResourceLayout(viewType: Int): Int = R.layout.item_nearby_spbu

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NearbySpbuViewHolder {
        return NearbySpbuViewHolder(getView(parent, viewType), itemClickListener, itemLongClickListener)
    }
}