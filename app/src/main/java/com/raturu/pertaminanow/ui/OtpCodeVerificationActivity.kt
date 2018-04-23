package com.raturu.pertaminanow.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.raturu.pertaminanow.PertaminaApp
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.presenter.OtpCodeVerificationPresenter
import com.raturu.pertaminanow.util.getColorValue
import kotlinx.android.synthetic.main.activity_otp_code_verification.*

/**
 * Created on : April 13, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class OtpCodeVerificationActivity : AppCompatActivity(), OtpCodeVerificationPresenter.View {
    private lateinit var otpCodeVerificationPresenter: OtpCodeVerificationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_code_verification)
        otpCodeVerificationPresenter = OtpCodeVerificationPresenter(this, PertaminaApp.instance.getComponent().accountRepository)

        otpCodeTextField.addTextChangedListener(textWatcher)

        submitButton.setOnClickListener {
            otpCodeVerificationPresenter.verify(otpCodeTextField.text.toString())
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
        return input.length == 4
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showEditProfilePage() {
        startActivity(
                EditProfileActivity.newIntent(this, true)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        )
    }

    override fun showErrorMessage(errorMessage: String) {
        Snackbar.make(submitButton, errorMessage, Snackbar.LENGTH_LONG).show()
    }
}