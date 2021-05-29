package com.study.app_viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: NumberViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 取得 viewmodel 的實體
        viewModel = ViewModelProvider(this).get(NumberViewModel::class.java)

        // 觀察者模式
        viewModel.currentNumber.observe(this, Observer {
            // 若 viewModel.currentNumber 發生變化, 要如何處理 ?
            tv_text.text = it.toString()
        })

        viewModel.currentBoolean.observe(this, Observer {
            // 若 viewModel.currentBoolean 發生變化, 要如何處理 ?
            tv_booleanText.text = it.toString()
        })

        btn_button.setOnClickListener {
            add()
        }

    }

    private fun add() {
        viewModel.currentNumber.value = ++viewModel.number
        viewModel.currentBoolean.value = viewModel.number % 2 == 0
    }

}