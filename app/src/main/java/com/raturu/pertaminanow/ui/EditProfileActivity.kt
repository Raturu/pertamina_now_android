package com.raturu.pertaminanow.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.raturu.pertaminanow.PertaminaApp
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.data.model.Account
import com.raturu.pertaminanow.presenter.EditProfilePresenter
import kotlinx.android.synthetic.main.activity_edit_profile.*

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class EditProfileActivity : AppCompatActivity(), EditProfilePresenter.View {
    private lateinit var editProfilePresenter: EditProfilePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        editProfilePresenter = EditProfilePresenter(this, PertaminaApp.instance.getComponent().accountRepository)
        editProfilePresenter.loadProfile()
    }

    override fun showLoading() {
        //TODO
    }

    override fun dismissLoading() {
        //TODO
    }

    override fun showProfile(account: Account) {
        //TODO
    }

    override fun showKtpVerificationPage() {
        startActivity(
                Intent(this, KtpVerificationActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        )
    }

    override fun onProfileUpdated(account: Account) {
        //TODO
        showProfile(account)
    }

    override fun showErrorMessage(errorMessage: String) {
        Snackbar.make(nameTextField, errorMessage, Snackbar.LENGTH_LONG).show()
    }
}