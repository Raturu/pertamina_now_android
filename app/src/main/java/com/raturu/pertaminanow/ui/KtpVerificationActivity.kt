package com.raturu.pertaminanow.ui

import android.app.AlertDialog
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.IsoDep
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.raturu.pertaminanow.PertaminaApp
import com.raturu.pertaminanow.R
import com.raturu.pertaminanow.presenter.KtpVerificationPresenter
import com.raturu.pertaminanow.util.getColorValue
import com.raturu.pertaminanow.util.toHex
import kotlinx.android.synthetic.main.activity_ktp_verification.*


/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
class KtpVerificationActivity : AppCompatActivity(), KtpVerificationPresenter.View {
    private lateinit var ktpVerificationPresenter: KtpVerificationPresenter
    private lateinit var nfcAdapter: NfcAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ktp_verification)
        supportActionBar?.title = "Aktivasi E-KTP"

        ktpVerificationPresenter = KtpVerificationPresenter(this, PertaminaApp.instance.getComponent().accountRepository)

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        registerReceiver(nfcStateReceiver, IntentFilter(NfcAdapter.ACTION_ADAPTER_STATE_CHANGED))

        nearbySpbuLink.setOnClickListener {
            startActivity(Intent(this, NearbySpbuActivity::class.java))
        }
    }

    private val nfcStateReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action.equals(NfcAdapter.ACTION_ADAPTER_STATE_CHANGED)) {
                checkIsNfcEnabled()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        checkIsNfcEnabled()
        enableReaderMode()
    }

    private fun checkIsNfcEnabled() {
        if (nfcAdapter.isEnabled) {
            showStep2View()
        } else {
            showStep1View()
        }
    }

    private fun showStep1View() {
        checkMark1.setColorFilter(getColorValue(R.color.divider))
        checkMark2.setColorFilter(getColorValue(R.color.divider))
        line.setBackgroundColor(getColorValue(R.color.divider))
        stepDescriptionTextView.setText(R.string.nfc_step_1)
        nfcIllustrationImageView.setImageResource(R.drawable.ic_nfc_step1)
        nfcIllustrationTextView.setText(R.string.nfc_illustration_step_1)
    }

    private fun showStep2View() {
        checkMark1.setColorFilter(getColorValue(R.color.primary))
        checkMark2.setColorFilter(getColorValue(R.color.divider))
        line.setBackgroundColor(getColorValue(R.color.primary))
        stepDescriptionTextView.setText(R.string.nfc_step_2)
        nfcIllustrationImageView.setImageResource(R.drawable.ic_nfc_step2)
        nfcIllustrationTextView.setText(R.string.nfc_illustration_step_2)
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
        val pendingIntent = PendingIntent.getActivity(this, 0,
                Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0)

        val intentFilter = IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED)
        intentFilter.addDataType("*/*")
        val intentFiltersArray = arrayOf(intentFilter)

        val techListsArray = arrayOf(arrayOf(IsoDep::class.java.name))
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFiltersArray, techListsArray)
    }

    private fun disableReaderMode() {
        nfcAdapter.disableForegroundDispatch(this)
    }

    private fun onKtpDataReceived(ktpSerialNumber: String) {
        if (ktpSerialNumber.startsWith("04") && ktpSerialNumber.endsWith("80") && ktpSerialNumber.length == 14) {
            ktpVerificationPresenter.verify(ktpSerialNumber)
        } else {
            showErrorMessage("Kartu tidak dikenali!")
        }
    }

    override fun showKtpVerifiedDialog() {
        checkMark2.setColorFilter(getColorValue(R.color.primary))
        AlertDialog.Builder(this)
                .setMessage("Selamat E-KTP berhasil diaktivasi, sekarang Anda bisa bertransaksi menggunakan E-KTP di SPBU.")
                .setPositiveButton("Oke", { dialog, _ ->
                    showHomePage()
                    dialog.dismiss()
                })
                .show()
    }

    private fun showHomePage() {
        startActivity(
                Intent(this, HomeActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        )
    }

    override fun showErrorMessage(errorMessage: String) {
        Snackbar.make(nearbySpbuLink, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(nfcStateReceiver)
    }
}