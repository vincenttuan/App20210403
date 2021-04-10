package com.applab.app_exchange
import yahoofinance.YahooFinance

fun getUsd(amount: Int): Double {
    val rate = YahooFinance.get("TWDUSD=x").quote.price.toDouble()
    return amount * rate
}