package com.raturu.pertaminanow.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.raturu.pertaminanow.PertaminaApp
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.presenter.TopUpBalancePresenter
import com.raturu.pertaminanow.util.getColorValue
import com.raturu.pertaminanow.util.hideKeyboard
import com.raturu.pertaminanow.util.toRupiahFormat
import kotlinx.android.synthetic.main.activity_top_up_balance.*

/**
 * Created on : April 21, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class TopUpBalanceActivity : AppCompatActivity(), TopUpBalancePresenter.View {
    private lateinit var topUpBalancePresenter: TopUpBalancePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_up_balance)

        supportActionBar?.let {
            it.title = "Top Up Saldo"
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        topUpBalancePresenter = TopUpBalancePresenter(this, PertaminaApp.instance.getComponent().accountRepository)

        amountTextField.addTextChangedListener(textWatcher)

        topUpBalancePresenter.loadBalance()

        submitButton.setOnClickListener {
            topUpBalancePresenter.topUpBalance(amountTextField.text.toString().toLong())
            hideKeyboard(submitButton)
        }
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            when {
                validateInput(s.toString()) -> enableSubmitButton()
                else -> disableSubmitButton()
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }

    private fun enableSubmitButton() {
        submitButton.setBackgroundColor(getColorValue(R.color.primary))
        submitButton.isEnabled = true
    }

    private fun disableSubmitButton() {
        submitButton.setBackgroundColor(getColorValue(R.color.divider))
        submitButton.isEnabled = false
    }

    private fun validateInput(input: String): Boolean {
        return input.isNotBlank() && input.toLong() >= 10000
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showBalance(amount: Long) {
        balanceTextView.text = amount.toRupiahFormat()
    }

    override fun onTopUpBalanceSuccess(amount: Long) {
        showBalance(amount)
        amountTextField.setText("")
        Snackbar.make(submitButton, "Saldo berhasil ditambah!", Snackbar.LENGTH_LONG).show()
    }

    override fun showErrorMessage(errorMessage: String) {
        Snackbar.make(submitButton, errorMessage, Snackbar.LENGTH_LONG).show()
    }
}