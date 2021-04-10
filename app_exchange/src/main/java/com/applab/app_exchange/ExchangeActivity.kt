package com.applab.app_exchange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_exchange.*
import yahoofinance.YahooFinance
import java.text.SimpleDateFormat
import java.util.*

class ExchangeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange)

        Thread(Runnable {
            val amount = intent.getIntExtra("amount", 0)
            val usd = getUSDByTWD(amount)
            runOnUiThread(Runnable {
                result_amount.text = "%.2f".format(usd)
                val time = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.TAIWAN)
                result_time.text = time.format(Date())
            })
        }).start()

    }

    fun onClick(view: View) {
        finish()
    }
}