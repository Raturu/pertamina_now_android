package com.raturu.pertaminanow.ui.adapter.viewholder

import android.location.Location
import android.view.View
import android.widget.TextView
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.data.model.Spbu
import com.raturu.pertaminanow.ui.adapter.BaseAdapter
import com.raturu.pertaminanow.util.LocationUtil

/**
 * Created on : April 21, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class NearbySpbuViewHolder(itemView: View,
                           itemClickListener: BaseAdapter.OnItemClickListener?,
                           itemLongClickListener: BaseAdapter.OnItemLongClickListener?)
    : BaseViewHolder<Spbu>(itemView, itemClickListener, itemLongClickListener) {

    private val spbuNameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
    private val spbuAddressTextView = itemView.findViewById<TextView>(R.id.addressTextView)
    private val distanceAndCityTextView = itemView.findViewById<TextView>(R.id.distanceAndCityTextView)
    private val navigationButton = itemView.findViewById<View>(R.id.navigationButton)

    init {
        navigationButton.setOnClickListener(this)
    }

    override fun bind(data: Spbu) {
        spbuNameTextView.text = data.name
        spbuAddressTextView.text = data.location.address
        computeDistance(data)
    }

    private fun computeDistance(spbu: Spbu) {
        val lastLocation = LocationUtil.getLastLocation()
        if (lastLocation.latitude != 0.0) {
            val spbuLocation = Location("")
            spbuLocation.latitude = spbu.location.latitude
            spbuLocation.longitude = spbu.location.longitude
            val distanceInMeters = lastLocation.distanceTo(spbuLocation)
            val distanceInKiloMeters = distanceInMeters / 1000
            distanceAndCityTextView.text = "${"%.2f".format(distanceInKiloMeters)} km - ${spbu.location.city}"
        } else {
            distanceAndCityTextView.text = "${spbu.location.city}"
        }
    }

    override fun onClick(v: View) {
        if (v == navigationButton) {
            super.onClick(v)
        }
    }
}