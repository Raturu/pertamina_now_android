package com.raturu.pertaminanow.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import com.raturu.pertaminanow.PertaminaApp
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.presenter.LoginPresenter
import com.raturu.pertaminanow.util.getColorValue
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class LoginActivity : AppCompatActivity(), LoginPresenter.View {
    private lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginPresenter = LoginPresenter(this, PertaminaApp.instance.getComponent().accountRepository)

        phoneNumberTextField.addTextChangedListener(textWatcher)

        loginButton.setOnClickListener {
            loginPresenter.auth("+62${phoneNumberTextField.text}")
        }

    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            when {
                validateInput(s.toString()) -> enableLoginButton()
                else -> disableLoginButton()
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }

    private fun enableLoginButton() {
        loginButton.setBackgroundColor(getColorValue(R.color.primary))
        loginButton.isEnabled = true
    }

    private fun disableLoginButton() {
        loginButton.setBackgroundColor(getColorValue(R.color.divider))
        loginButton.isEnabled = false
    }

    private fun validateInput(input: String): Boolean {
        return "+62$input".length >= 13 && Patterns.PHONE.matcher("+62$input").matches()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showOtpCodeVerificationPage() {
        startActivity(
                Intent(this, OtpCodeVerificationActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        )
    }

    override fun showErrorMessage(errorMessage: String) {
        Snackbar.make(loginButton, errorMessage, Snackbar.LENGTH_LONG).show()
    }
}