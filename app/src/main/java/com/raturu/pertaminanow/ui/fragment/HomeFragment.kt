package com.raturu.pertaminanow.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.ui.adapter.HomePagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class HomeFragment : Fragment() {
    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val homePagerAdapter = HomePagerAdapter(childFragmentManager,
                arrayListOf(PromoFragment.newInstance(), PromoFragment.newInstance(),
                        PromoFragment.newInstance(), PromoFragment.newInstance()),
                arrayListOf("All", "Special Promo", "Flash Deals", "Payment")
        )
        viewPager.adapter = homePagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}