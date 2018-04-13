package com.raturu.pertaminanow.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raturu.pertaminanow.PertaminaApp
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.presenter.AccountPresenter
import com.raturu.pertaminanow.ui.EditProfileActivity
import com.raturu.pertaminanow.ui.LoginActivity
import com.raturu.pertaminanow.ui.VouchersActivity
import kotlinx.android.synthetic.main.fragment_account.*

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class AccountFragment : Fragment(), AccountPresenter.View {

    private lateinit var accountPresenter: AccountPresenter

    companion object {
        fun newInstance(): AccountFragment = AccountFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_account, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        accountPresenter = AccountPresenter(this, PertaminaApp.instance.getComponent().accountRepository)

        editLink.setOnClickListener {
            startActivity(Intent(activity, EditProfileActivity::class.java))
        }

        pointButton.setOnClickListener {
            startActivity(Intent(activity, VouchersActivity::class.java))
        }

        logoutLink.setOnClickListener {
            accountPresenter.logout()
        }
    }

    override fun showLoginPage() {
        startActivity(
                Intent(activity, LoginActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        )
    }
}