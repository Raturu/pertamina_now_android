package com.raturu.pertaminanow.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raturu.pertaminanow.ui.adapter.viewholder.BaseViewHolder


/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
abstract class BaseAdapter<Data, Holder : BaseViewHolder<Data>>(private val context: Context) : RecyclerView.Adapter<Holder>() {
    protected val data: MutableList<Data> = mutableListOf()

    protected var itemClickListener: OnItemClickListener? = null
    protected var itemLongClickListener: OnItemLongClickListener? = null

    protected fun getView(parent: ViewGroup, viewType: Int): View {
        return LayoutInflater.from(context).inflate(getItemResourceLayout(viewType), parent, false)
    }

    protected abstract fun getItemResourceLayout(viewType: Int): Int

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(position: Int)
    }

    fun setOnItemLongClickListener(itemLongClickListener: OnItemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener
    }

    fun add(item: Data) {
        data.add(item)
        notifyItemInserted(data.size - 1)
    }

    fun add(item: Data, position: Int) {
        data.add(position, item)
        notifyItemInserted(position)
    }

    fun add(items: List<Data>) {
        items.forEach { data.add(it) }
        notifyDataSetChanged()
    }

    fun addOrUpdate(item: Data) {
        val i = data.indexOf(item)
        if (i >= 0) {
            data[i] = item
            notifyItemChanged(i)
        } else {
            add(item)
        }
    }

    fun addOrUpdate(items: List<Data>) {
        items.forEach {
            val position = data.indexOf(it)
            if (position >= 0) {
                data[position] = it
            } else {
                data.add(it)
            }
        }
        notifyDataSetChanged()
    }

    fun remove(position: Int) {
        if (position >= 0 && position < data.size) {
            data.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun remove(item: Data) {
        val position = data.indexOf(item)
        remove(position)
    }

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }
}