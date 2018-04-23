package com.raturu.pertaminanow.ui.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.raturu.pertaminanow.ui.adapter.BaseAdapter.OnItemClickListener
import com.raturu.pertaminanow.ui.adapter.BaseAdapter.OnItemLongClickListener

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
abstract class BaseViewHolder<Data>(itemView: View,
                                    private val itemClickListener: OnItemClickListener? = null,
                                    private val itemLongClickListener: OnItemLongClickListener? = null)
    : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {

    init {
        itemView.setOnClickListener(this)
        itemView.setOnLongClickListener(this)
    }

    abstract fun bind(data: Data)

    override fun onClick(v: View) {
        itemClickListener?.onItemClick(adapterPosition)
    }

    override fun onLongClick(v: View): Boolean {
        itemLongClickListener?.let {
            it.onItemLongClick(adapterPosition)
            return true
        }
        return false
    }
}