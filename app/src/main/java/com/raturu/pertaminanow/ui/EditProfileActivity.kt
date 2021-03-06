package com.raturu.pertaminanow.ui

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Patterns
import android.widget.DatePicker
import com.bumptech.glide.Glide
import com.raturu.pertaminanow.PertaminaApp
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.data.model.Account
import com.raturu.pertaminanow.data.model.Gender
import com.raturu.pertaminanow.presenter.EditProfilePresenter
import com.raturu.pertaminanow.util.fromFullDateFormat
import com.raturu.pertaminanow.util.toFullDateFormat
import kotlinx.android.synthetic.main.activity_edit_profile.*
import java.util.*

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class EditProfileActivity : AppCompatActivity(), EditProfilePresenter.View, DatePickerDialog.OnDateSetListener {

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

        pickDateButton.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        DatePickerDialog(this, this, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH]).show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar[Calendar.YEAR] = year
        calendar[Calendar.MONTH] = month
        calendar[Calendar.DAY_OF_MONTH] = dayOfMonth
        dateOfBirthTextField.setText(calendar.time.toFullDateFormat())
    }

    private fun validateInput(): Boolean {
        if (nameTextField.text.toString().isBlank()) {
            nameTextField.error = "Mohon masukan nama anda!"
            nameTextField.requestFocus()
            return false
        }

        if (emailTextField.text.toString().isBlank()) {
            emailTextField.error = "Mohon masukan email anda!"
            emailTextField.requestFocus()
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailTextField.text.toString()).matches()) {
            emailTextField.error = "Mohon masukan email yang valid!"
            emailTextField.requestFocus()
            return false
        }

        if (dateOfBirthTextField.text.toString().isBlank()) {
            dateOfBirthTextField.error = "Mohon isi tanggal lahir anda!"
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
        Snackbar.make(nameTextField, "Profil berhasil diubah!", Snackbar.LENGTH_LONG).show()
    }

    override fun showErrorMessage(errorMessage: String) {
        Snackbar.make(nameTextField, errorMessage, Snackbar.LENGTH_LONG).show()
    }
}