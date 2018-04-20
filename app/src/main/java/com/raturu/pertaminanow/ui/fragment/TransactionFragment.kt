package com.raturu.pertaminanow.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.data.model.Location
import com.raturu.pertaminanow.data.model.Spbu
import com.raturu.pertaminanow.data.model.Transaction
import com.raturu.pertaminanow.ui.TransactionDetailActivity
import com.raturu.pertaminanow.ui.adapter.TransactionAdapter
import kotlinx.android.synthetic.main.fragment_transaction.*
import java.util.*

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class TransactionFragment : Fragment() {
    companion object {
        fun newInstance(): TransactionFragment = TransactionFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val transactionAdapter = TransactionAdapter(activity!!)
        transactionAdapter.setOnItemClickListener {
            startActivity(Intent(activity, TransactionDetailActivity::class.java))
        }

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = transactionAdapter

        transactionAdapter.add(generateDummyData(30))
    }

    private fun generateDummyData(count: Int): List<Transaction> {
        return (1..count).map {
            Transaction("$it", Date(), it.toLong(), it.toDouble(), it.toLong(),
                    Spbu("", "", Location("", "", "", 0.0, 0.0)), 0)
        }
    }
}