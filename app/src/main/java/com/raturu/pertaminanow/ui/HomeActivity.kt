package com.raturu.pertaminanow.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.ui.fragment.AccountFragment
import com.raturu.pertaminanow.ui.fragment.HomeFragment
import com.raturu.pertaminanow.ui.fragment.TransactionFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        navigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
        navigationView.selectedItemId = R.id.navigation_home
    }

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                openFragment(HomeFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_transaction -> {
                openFragment(TransactionFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_account -> {
                openFragment(AccountFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
        }
        return@OnNavigationItemSelectedListener false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
