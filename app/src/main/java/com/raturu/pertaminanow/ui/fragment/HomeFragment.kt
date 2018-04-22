package com.raturu.pertaminanow.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raturu.pertaminanow.PertaminaApp
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.data.model.Promo
import com.raturu.pertaminanow.presenter.HomePresenter
import com.raturu.pertaminanow.ui.TopUpBalanceActivity
import com.raturu.pertaminanow.ui.VouchersActivity
import com.raturu.pertaminanow.ui.adapter.HomePagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class HomeFragment : Fragment(), HomePresenter.View {
    private lateinit var homePresenter: HomePresenter

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homePresenter = HomePresenter(this, PertaminaApp.instance.getComponent().promoRepository)
        homePresenter.loadPromoCategories()

        pointButton.setOnClickListener {
            startActivity(Intent(activity, VouchersActivity::class.java))
        }

        balanceButton.setOnClickListener {
            startActivity(Intent(activity, TopUpBalanceActivity::class.java))
        }
    }

    override fun showLoading() {
        //TODO
    }

    override fun dismissLoading() {
        //TODO
    }

    override fun showPromoFragments(promoCategories: List<Promo.Category>) {
        val fragments = mutableListOf(PromoFragment.newInstance())
        fragments.addAll(promoCategories.map { PromoFragment.newInstance(it) })

        val titles = mutableListOf("Semua")
        titles.addAll(promoCategories.map { it.name })

        viewPager.adapter = HomePagerAdapter(childFragmentManager, fragments, titles)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun showErrorMessage(errorMessage: String) {
        Snackbar.make(viewPager, errorMessage, Snackbar.LENGTH_LONG).show()
    }
}