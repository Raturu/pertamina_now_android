package com.raturu.pertaminanow.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
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
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    companion object {
        private const val PERMISSIONS_REQUEST_LOCATION = 1212
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nearby_spbu)
        supportActionBar?.title = "SPBU Terdekat"

        val appComponent = PertaminaApp.instance.getComponent()
        nearbySpbuPresenter = NearbySpbuPresenter(this, appComponent.accountRepository, appComponent.spbuRepository)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

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

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_COARSE_LOCATION)) {

                val snackbar = Snackbar.make(recyclerView, "Mohon izinkan Pertamina Now mengakses lokasi anda!", Snackbar.LENGTH_INDEFINITE)
                snackbar.setAction("OK", {
                    requestLocationPermission()
                })

            } else {
                requestLocationPermission()
            }
        } else {
            loadNearbySpbu()
        }

        nearbySpbuPresenter.loadKtpVerifySpbuCode()
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                PERMISSIONS_REQUEST_LOCATION)
    }

    @SuppressLint("MissingPermission")
    private fun loadNearbySpbu() {
        fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    nearbySpbuPresenter.loadNearbySpbu(location?.latitude
                            ?: 0.0, location?.longitude ?: 0.0)
                }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSIONS_REQUEST_LOCATION -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    loadNearbySpbu()
                } else {
                    requestLocationPermission()
                }
            }
        }
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