package com.raturu.pertaminanow.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.data.model.Promo
import com.raturu.pertaminanow.util.ColorUtil
import com.raturu.pertaminanow.util.LocationUtil
import com.raturu.pertaminanow.util.toSimpleMonthFormat
import kotlinx.android.synthetic.main.activity_nearby_spbu.*
import kotlinx.android.synthetic.main.activity_promo_detail.*
import kotlinx.android.synthetic.main.item_nearby_spbu.*

/**
 * Created on : April 21, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class PromoDetailActivity : AppCompatActivity() {
    private lateinit var promo: Promo
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    companion object {
        private const val EXTRA_PROMO = "extra_promo"
        private const val PERMISSIONS_REQUEST_LOCATION = 1212

        fun newIntent(context: Context, promo: Promo): Intent {
            val intent = Intent(context, PromoDetailActivity::class.java)
            intent.putExtra(EXTRA_PROMO, promo)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promo_detail)
        promo = intent.getParcelableExtra(EXTRA_PROMO)

        supportActionBar?.let {
            it.title = "Promo"
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
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
            loadLastLocation()
        }

        bindPromoData()
    }

    @SuppressLint("MissingPermission")
    private fun loadLastLocation() {
        fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    location?.let {
                        LocationUtil.saveLastLocation(it)
                        computeDistance()
                    }
                }
    }

    private fun computeDistance() {
        val lastLocation = LocationUtil.getLastLocation()
        if (lastLocation.latitude != 0.0) {
            val spbuLocation = Location("")
            spbuLocation.latitude = promo.spbu.location.latitude
            spbuLocation.longitude = promo.spbu.location.longitude
            val distanceInMeters = lastLocation.distanceTo(spbuLocation)
            val distanceInKiloMeters = distanceInMeters / 1000
            distanceAndCityTextView.text = "${"%.2f".format(distanceInKiloMeters)} km - ${promo.spbu.location.city}"
        } else {
            distanceAndCityTextView.text = "${promo.spbu.location.city}"
        }
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                PERMISSIONS_REQUEST_LOCATION)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSIONS_REQUEST_LOCATION -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    loadLastLocation()
                } else {
                    requestLocationPermission()
                }
            }
        }
    }

    private fun bindPromoData() {
        bannerImageView.setBackgroundColor(ColorUtil.randomColor())
        Glide.with(this).load(promo.imageUrl).into(bannerImageView)
        promoTitleTextView.text = promo.title
        promoPeriodTextView.text = "Periode: ${promo.startDate.toSimpleMonthFormat()} - ${promo.endDate.toSimpleMonthFormat()}"
        promoDescriptionTextView.text = promo.description

        nameTextView.text = promo.spbu.name
        addressTextView.text = promo.spbu.location.address
        computeDistance()

        pointTextView.text = "${promo.point} pts"

        navigationButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr="
                            + promo.spbu.location.latitude + ","
                            + promo.spbu.location.longitude))
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.promo_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when {
            item.itemId == R.id.share -> sharePromo()
            item.itemId == android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun sharePromo() {

    }
}