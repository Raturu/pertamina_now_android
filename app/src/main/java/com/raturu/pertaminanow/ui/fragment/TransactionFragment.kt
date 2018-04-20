package com.raturu.pertaminanow.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raturu.pertaminanow.PertaminaApp
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.data.model.Transaction
import com.raturu.pertaminanow.presenter.TransactionPresenter
import com.raturu.pertaminanow.ui.TransactionDetailActivity
import com.raturu.pertaminanow.ui.adapter.TransactionAdapter
import kotlinx.android.synthetic.main.fragment_transaction.*

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class TransactionFragment : Fragment(), TransactionPresenter.View {
    private lateinit var transactionPresenter: TransactionPresenter
    private lateinit var transactionAdapter: TransactionAdapter

    companion object {
        fun newInstance(): TransactionFragment = TransactionFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        transactionPresenter = TransactionPresenter(this, PertaminaApp.instance.getComponent().transactionRepository)

        transactionAdapter = TransactionAdapter(activity!!)
        transactionAdapter.setOnItemClickListener {
            startActivity(Intent(activity, TransactionDetailActivity::class.java))
        }

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = transactionAdapter

        transactionPresenter.loadTransactionHistories()
    }

    override fun showLoading() {
        //TODO
    }

    override fun dismissLoading() {
        //TODO
    }

    override fun showTransactionHistories(transactionHistories: List<Transaction>) {
        transactionAdapter.addOrUpdate(transactionHistories)
    }

    override fun showErrorMessage(errorMessage: String) {
        Snackbar.make(recyclerView, errorMessage, Snackbar.LENGTH_LONG).show()
    }
}