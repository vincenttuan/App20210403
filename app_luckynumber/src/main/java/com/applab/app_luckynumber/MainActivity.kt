package com.applab.app_luckynumber

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.applab.app_luckynumber.utils.LuckyNumber
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var luckyNumber = LuckyNumber()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    // 按下 submit button 後所要執行的邏輯
    fun onClickSubmitButton(view: View) {
        val guess = inputNumber.text.toString().toInt()
        //val ans = luckyNumber.ans
        val result = luckyNumber.validate(guess)
        if(result > 0) {
            // 通知 Toast
            Toast.makeText(this, R.string.too_big, Toast.LENGTH_SHORT).show()
            // 通知 AlertDialog
            AlertDialog.Builder(this)
                .setTitle(R.string.title)
                .setMessage(R.string.too_big)
                .setPositiveButton(R.string.ok, listener2)
                .show()

        } else if(result < 0) {
            Toast.makeText(this, R.string.too_small, Toast.LENGTH_SHORT).show()
            AlertDialog.Builder(this)
                .setTitle(R.string.title)
                .setMessage(R.string.too_small)
                .setPositiveButton(R.string.ok, listener2)
                .show()

        } else {
            Toast.makeText(this, R.string.bingo, Toast.LENGTH_SHORT).show()
            AlertDialog.Builder(this)
                .setTitle(R.string.title)
                .setMessage(R.string.bingo)
                .setPositiveButton(R.string.replay, listener)
                //.setNeutralButton(R.string.cancel, null)
                .setNegativeButton(R.string.exit, listener)
                .show()
        }
    }
    // 實作一個 AlertDialog 的 onClick 監聽器
    val listener = DialogInterface.OnClickListener { dialog, which ->
        when(which) {
            DialogInterface.BUTTON_POSITIVE -> {
                // 重新產生 LuckyNumber 物件
                luckyNumber = LuckyNumber()
            }
            DialogInterface.BUTTON_NEGATIVE -> {
                finish() // Activity運行結束
            }
        }
    }

    val listener2 = DialogInterface.OnClickListener { dialog, which ->
        inputNumber.selectAll()
    }

}