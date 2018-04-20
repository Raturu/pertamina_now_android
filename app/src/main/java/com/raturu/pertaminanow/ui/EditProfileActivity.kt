package com.raturu.pertaminanow.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Patterns
import com.bumptech.glide.Glide
import com.raturu.pertaminanow.PertaminaApp
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.data.model.Account
import com.raturu.pertaminanow.data.model.Gender
import com.raturu.pertaminanow.presenter.EditProfilePresenter
import com.raturu.pertaminanow.util.fromFullDateFormat
import com.raturu.pertaminanow.util.toFullDateFormat
import kotlinx.android.synthetic.main.activity_edit_profile.*

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class EditProfileActivity : AppCompatActivity(), EditProfilePresenter.View {
    private lateinit var editProfilePresenter: EditProfilePresenter
    private lateinit var account: Account

    companion object {
        private const val EXTRA_FROM_OTP_PAGE = "extra_from_otp_page"

        fun newIntent(context: Context, fromOtpPage: Boolean = false): Intent {
            val intent = Intent(context, EditProfileActivity::class.java)
            intent.putExtra(EXTRA_FROM_OTP_PAGE, fromOtpPage)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        editProfilePresenter = EditProfilePresenter(this, PertaminaApp.instance.getComponent().accountRepository)
        editProfilePresenter.loadProfile()

        saveButton.setOnClickListener {
            if (validateInput()) {
                editProfilePresenter.updateProfile(
                        account.copy(user = account.user.copy(
                                email = emailTextField.text.toString(),
                                name = nameTextField.text.toString(),
                                dateOfBirth = dateOfBirthTextField.text.toString().fromFullDateFormat(),
                                gender = if (maleRadioButton.isChecked) Gender.MALE else Gender.FEMALE
                        )),
                        intent.getBooleanExtra(EXTRA_FROM_OTP_PAGE, false)
                )
            }
        }
    }

    private fun validateInput(): Boolean {
        if (nameTextField.text.toString().isBlank()) {
            nameTextField.error = "Please insert your name!"
            nameTextField.requestFocus()
            return false
        }

        if (emailTextField.text.toString().isBlank()) {
            emailTextField.error = "Please insert your email!"
            emailTextField.requestFocus()
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailTextField.text.toString()).matches()) {
            emailTextField.error = "Please insert a valid email!"
            emailTextField.requestFocus()
            return false
        }

        if (dateOfBirthTextField.text.toString().isBlank()) {
            dateOfBirthTextField.error = "Please insert your birth date!"
            return false
        }

        return true
    }

    override fun showLoading() {
        //TODO
    }

    override fun dismissLoading() {
        //TODO
    }

    override fun showProfile(account: Account) {
        this.account = account
        Glide.with(this)
                .load(account.user.avatar)
                .error(R.drawable.ic_account)
                .placeholder(R.drawable.ic_account)
                .into(avatarImageView)
        nameTextField.setText(account.user.name)
        phoneNumberTextField.setText(account.user.phoneNumber)
        emailTextField.setText(account.user.email)
        dateOfBirthTextField.setText(account.user.dateOfBirth.toFullDateFormat())
        when (account.user.gender) {
            Gender.MALE -> maleRadioButton.isChecked = true
            Gender.FEMALE -> femaleRadioButton.isChecked = true
        }
    }

    override fun showHomePage() {
        startActivity(
                Intent(this, HomeActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        )
    }

    override fun showKtpVerificationPage() {
        startActivity(
                Intent(this, KtpVerificationActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        )
    }

    override fun onProfileUpdated(account: Account) {
        showProfile(account)
        Snackbar.make(nameTextField, "Profile Updated!", Snackbar.LENGTH_LONG).show()
    }

    override fun showErrorMessage(errorMessage: String) {
        Snackbar.make(nameTextField, errorMessage, Snackbar.LENGTH_LONG).show()
    }
}