package com.applab.app_abnumber

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.applab.app_abnumber.utils.ABNumber
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private var ab = ABNumber()
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ab.setAns()
    }

    fun onClickSubmitButton(view: View) {
        val guess = inputNumber.text.toString()
        val result = ab.getResult(guess)
        Log.d(TAG, Arrays.toString(result))
        // result 資料呈現
        val log = "${result[0]} A ${result[1]} B"
        // log 資料累積
        resultLog.text = "$guess -> $log \n${resultLog.text.toString()}"
        inputNumber.selectAll()
        if(result[0] == 4) {
            AlertDialog.Builder(this)
                .setTitle(R.string.result)
                .setTitle(R.string.bingo)
                .setPositiveButton(R.string.replay, listener)
                .setNegativeButton(R.string.exit, listener)
                .show()
        }

    }

    val listener = DialogInterface.OnClickListener { dialog, which ->
        when(which) {
            DialogInterface.BUTTON_POSITIVE -> {
                ab = ABNumber()
                ab.setAns()
                resultLog.text = "" // resultLog 清空
            }
            DialogInterface.BUTTON_NEGATIVE -> {
                finish() // Activity運行結束
            }
        }
    }

}