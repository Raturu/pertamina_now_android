package com.raturu.pertaminanow.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.data.model.Transaction
import com.raturu.pertaminanow.util.toFullTimeFormat
import com.raturu.pertaminanow.util.toRupiahFormat
import kotlinx.android.synthetic.main.activity_transaction_detail.*

/**
 * Created on : April 13, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class TransactionDetailActivity : AppCompatActivity() {

    private lateinit var transaction: Transaction

    companion object {
        private const val EXTRA_TRANSACTION = "extra_transaction"

        fun newIntent(context: Context, transaction: Transaction): Intent {
            val intent = Intent(context, TransactionDetailActivity::class.java)
            intent.putExtra(EXTRA_TRANSACTION, transaction)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_detail)
        supportActionBar?.let {
            it.title = "Transaction Detail"
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        transaction = intent.getParcelableExtra(EXTRA_TRANSACTION)
        bindTransactionData()
    }

    private fun bindTransactionData() {
        orderNoTextField.setText(transaction.id)
        createdAtTextField.setText(transaction.createdAt.toFullTimeFormat())
        priceTextField.setText(transaction.price.toRupiahFormat())
        paidAmountTextField.setText(transaction.paidAmount.toRupiahFormat())
        gasolineAmountTextField.setText("${transaction.gasolineAmount} Liter")
        gasolineTypeField.setText(transaction.gasolineType)
        spbuTextField.setText(transaction.spbu.name)
        bonusPointsTextField.setText("${transaction.point} pts")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when {
            item.itemId == android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}