package com.raturu.pertaminanow.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.data.model.Promo
import com.raturu.pertaminanow.util.ColorUtil
import com.raturu.pertaminanow.util.toSimpleMonthFormat
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

    companion object {
        private const val EXTRA_PROMO = "extra_promo"

        fun newIntent(context: Context, promo: Promo): Intent {
            val intent = Intent(context, PromoDetailActivity::class.java)
            intent.putExtra(EXTRA_PROMO, promo)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promo_detail)
        supportActionBar?.let {
            it.title = "Promo"
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        promo = intent.getParcelableExtra(EXTRA_PROMO)
        bindPromoData()
    }

    private fun bindPromoData() {
        bannerImageView.setBackgroundColor(ColorUtil.randomColor())
        Glide.with(this).load(promo.imageUrl).into(bannerImageView)
        promoTitleTextView.text = promo.title
        promoPeriodTextView.text = "Periode: ${promo.startDate.toSimpleMonthFormat()} - ${promo.endDate.toSimpleMonthFormat()}"
        promoDescriptionTextView.text = promo.description

        nameTextView.text = promo.spbu.name
        addressTextView.text = promo.spbu.location.address
        distanceAndCityTextView.text = "2.0 km - ${promo.spbu.location.city}"

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