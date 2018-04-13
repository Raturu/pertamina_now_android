package com.raturu.pertaminanow.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.data.model.Voucher
import com.raturu.pertaminanow.ui.adapter.VoucherAdapter
import com.raturu.pertaminanow.util.ImageUtil
import kotlinx.android.synthetic.main.fragment_transaction.*

/**
 * Created on : April 13, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class VouchersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vouchers)
        supportActionBar?.let {
            it.title = "Vouchers"
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }
        val voucherAdapter = VoucherAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = voucherAdapter

        voucherAdapter.add(generateDummyData(30))
    }

    private fun generateDummyData(count: Int): List<Voucher> {
        return (1..count).map { Voucher("$it", "FREE Rp. 25.000.00 at Halodoc App", it, ImageUtil.getImage(it)) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when {
            item.itemId == android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}