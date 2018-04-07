package com.raturu.pertaminanow.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Patterns
import com.raturu.pertaminanow.PertaminaApp
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class LoginActivity : AppCompatActivity(), LoginPresenter.View {
    private lateinit var loginPresenter: LoginPresenter

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait!")

        loginPresenter = LoginPresenter(this, PertaminaApp.instance.getComponent().accountRepository)
        loginPresenter.start()

        loginButton.setOnClickListener {
            if (validateInput()) {
                loginPresenter.login(emailTextField.text.toString(), passwordTextField.text.toString())
            }
        }

        registerLink.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun validateInput(): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(emailTextField.text.toString()).matches()) {
            emailTextField.error = "Please insert a valid e-mail address!"
            return false
        }

        return true
    }

    override fun showLoading() {
        progressDialog.show()
    }

    override fun dismissLoading() {
        progressDialog.dismiss()
    }

    override fun showHomePage() {
        startActivity(
                Intent(this, MainActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        )
    }

    override fun showErrorMessage(errorMessage: String) {
        Snackbar.make(loginButton, errorMessage, Snackbar.LENGTH_LONG).show()
    }
}