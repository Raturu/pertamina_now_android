package com.raturu.pertaminanow.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.raturu.pertaminanow.PertaminaApp
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.data.model.Voucher
import com.raturu.pertaminanow.presenter.VouchersPresenter
import com.raturu.pertaminanow.ui.adapter.VoucherAdapter
import com.raturu.pertaminanow.util.ImageUtil
import com.raturu.pertaminanow.util.toPointFormat
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_transaction.*

/**
 * Created on : April 13, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class VouchersActivity : AppCompatActivity(), VouchersPresenter.View {
    private lateinit var vouchersPresenter: VouchersPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vouchers)
        supportActionBar?.let {
            it.title = "Vouchers"
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        vouchersPresenter = VouchersPresenter(this, PertaminaApp.instance.getComponent().accountRepository)
        vouchersPresenter.loadPoint()

        val voucherAdapter = VoucherAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = voucherAdapter

        voucherAdapter.add(generateDummyData(30))
    }

    private fun generateDummyData(count: Int): List<Voucher> {
        return (1..count).map { Voucher("$it", "GRATIS GO-Pay Rp. 25.000.00 pada aplikasi Go-Jek", it, ImageUtil.getImage(it)) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when {
            item.itemId == android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showPoint(point: Int) {
        pointTextView.text = "${point.toPointFormat()} pts"
    }

    override fun showErrorMessage(errorMessage: String) {
        Snackbar.make(recyclerView, errorMessage, Snackbar.LENGTH_LONG).show()
    }
}