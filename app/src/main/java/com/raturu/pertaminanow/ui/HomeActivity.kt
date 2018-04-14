package com.raturu.pertaminanow.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.ui.fragment.AccountFragment
import com.raturu.pertaminanow.ui.fragment.HomeFragment
import com.raturu.pertaminanow.ui.fragment.TransactionFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private var notificationMenuItem: MenuItem? = null

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
                notificationMenuItem?.isVisible = true
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_transaction -> {
                openFragment(TransactionFragment.newInstance())
                notificationMenuItem?.isVisible = false
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_account -> {
                openFragment(AccountFragment.newInstance())
                notificationMenuItem?.isVisible = false
                return@OnNavigationItemSelectedListener true
            }
        }
        return@OnNavigationItemSelectedListener false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        notificationMenuItem = menu?.findItem(R.id.notifications)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when {
            item.itemId == R.id.notifications -> openNotificationsPage()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openNotificationsPage() {
        startActivity(Intent(this, NotificationsActivity::class.java))
    }
}
