package com.raturu.pertaminanow.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.data.model.Location
import com.raturu.pertaminanow.data.model.Promo
import com.raturu.pertaminanow.data.model.Spbu
import com.raturu.pertaminanow.ui.adapter.PromoAdapter
import com.raturu.pertaminanow.util.ImageUtil
import kotlinx.android.synthetic.main.fragment_promo.*
import java.util.*

/**
 * Created on : April 13, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class PromoFragment : Fragment() {
    companion object {
        fun newInstance(): PromoFragment = PromoFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_promo, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val promoAdapter = PromoAdapter(activity!!)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = promoAdapter

        promoAdapter.add(generateDummyData(30))
    }

    private fun generateDummyData(count: Int): List<Promo> {
        return (1..count).map {
            Promo("$it", Promo.Category("", ""), "Diskon Pertamax Hingga 75% Khusus Untuk Pengguna Baru",
                    "", ImageUtil.getImage(it), Date(), Date(),
                    Spbu("", "", Location("", "", "", 0.0, 0.0)), 0)
        }
    }
}