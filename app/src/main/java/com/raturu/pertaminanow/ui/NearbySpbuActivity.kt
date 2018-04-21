package com.raturu.pertaminanow.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.raturu.pertaminanow.PertaminaApp
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.data.model.Spbu
import com.raturu.pertaminanow.presenter.NearbySpbuPresenter
import com.raturu.pertaminanow.ui.adapter.NearbySpbuAdapter
import kotlinx.android.synthetic.main.activity_nearby_spbu.*


/**
 * Created on : April 21, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class NearbySpbuActivity : AppCompatActivity(), NearbySpbuPresenter.View {
    private lateinit var nearbySpbuPresenter: NearbySpbuPresenter
    private lateinit var nearbySpbuAdapter: NearbySpbuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nearby_spbu)
        supportActionBar?.title = "SPBU Terdekat"

        val appComponent = PertaminaApp.instance.getComponent()
        nearbySpbuPresenter = NearbySpbuPresenter(this, appComponent.accountRepository, appComponent.spbuRepository)

        nearbySpbuAdapter = NearbySpbuAdapter(this)
        nearbySpbuAdapter.setOnItemClickListener {
            val spbu = nearbySpbuAdapter.data[it]
            val intent = Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr="
                            + spbu.location.latitude + ","
                            + spbu.location.longitude))
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = nearbySpbuAdapter

        nearbySpbuPresenter.loadKtpVerifySpbuCode()
        nearbySpbuPresenter.loadNearbySpbu()
    }

    override fun showKtpVerifySpbuCode(ktpVerifySpbuCode: String) {
        ktpVerifySpbuCodeTextView.text = ktpVerifySpbuCode
    }

    override fun showNearbySpbu(nearbySpbu: List<Spbu>) {
        nearbySpbuAdapter.addOrUpdate(nearbySpbu)
    }

    override fun showErrorMessage(errorMessage: String) {
        Snackbar.make(recyclerView, errorMessage, Snackbar.LENGTH_LONG).show()
    }
}