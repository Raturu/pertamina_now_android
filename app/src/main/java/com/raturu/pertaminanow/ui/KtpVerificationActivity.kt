package com.raturu.pertaminanow.ui

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.IsoDep
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.util.toHex
import timber.log.Timber


/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class KtpVerificationActivity : AppCompatActivity() {

    private lateinit var nfcAdapter: NfcAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ktp_verification)

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
    }

    override fun onResume() {
        super.onResume()
        enableReaderMode()
    }

    override fun onPause() {
        super.onPause()
        disableReaderMode()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val tag: Tag? = intent?.getParcelableExtra(NfcAdapter.EXTRA_TAG)
        tag?.let { onKtpDataReceived(it.id.toHex()) }
    }

    private fun enableReaderMode() {
        val pendingIntent = PendingIntent.getActivity(this, 0, Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0)

        val intentFilter = IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED)
        intentFilter.addDataType("*/*")
        val intentFiltersArray = arrayOf(intentFilter)

        val techListsArray = arrayOf(arrayOf(IsoDep::class.java.name))
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFiltersArray, techListsArray);
    }

    private fun disableReaderMode() {
        nfcAdapter.disableForegroundDispatch(this)
    }

    private fun onKtpDataReceived(ktpSerialNumber: String) {
        Timber.d("Ktp SN: $ktpSerialNumber")
    }
}