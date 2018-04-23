package com.raturu.pertaminanow.ui.fragment

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raturu.pertaminanow.PertaminaApp
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.data.model.Promo
import com.raturu.pertaminanow.presenter.PromoPresenter
import com.raturu.pertaminanow.ui.PromoDetailActivity
import com.raturu.pertaminanow.ui.adapter.PromoAdapter
import kotlinx.android.synthetic.main.fragment_promo.*

/**
 * Created on : April 13, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class PromoFragment : Fragment(), PromoPresenter.View {
    private lateinit var promoPresenter: PromoPresenter
    private lateinit var promoAdapter: PromoAdapter

    companion object {
        private const val EXTRA_PROMO_CATEGORY = "extra_promo_category"

        fun newInstance(promoCategory: Promo.Category? = null): PromoFragment {
            val fragment = PromoFragment()
            val bundle = Bundle()
            bundle.putParcelable(EXTRA_PROMO_CATEGORY, promoCategory)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_promo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        promoPresenter = PromoPresenter(this, PertaminaApp.instance.getComponent().promoRepository)

        promoAdapter = PromoAdapter(activity!!)
        promoAdapter.setOnItemClickListener {
            startActivity(PromoDetailActivity.newIntent(activity!!, promoAdapter.data[it]))
        }

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = promoAdapter

        promoPresenter.loadPromos(arguments?.getParcelable(EXTRA_PROMO_CATEGORY))
    }

    override fun showLoading() {
        //TODO
    }

    override fun dismissLoading() {
        //TODO
    }

    override fun showPromos(promos: List<Promo>) {
        promoAdapter.addOrUpdate(promos)
    }

    override fun showErrorMessage(errorMessage: String) {
        Snackbar.make(recyclerView, errorMessage, Snackbar.LENGTH_LONG).show()
    }
}