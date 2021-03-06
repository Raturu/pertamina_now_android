package com.raturu.pertaminanow.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created on : April 07, 2018
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
private val fullDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("ID"))
private val simpleMonthFormat = SimpleDateFormat("dd MMM yyyy", Locale("ID"))
private val fullTimeFormat = SimpleDateFormat("EEEE, dd MMM yyyy, HH:mm", Locale("ID"))

fun Date.toFullDateFormat(): String = fullDateFormat.format(this)

fun String.fromFullDateFormat(): Date = fullDateFormat.parse(this)

fun Date.toSimpleMonthFormat(): String = simpleMonthFormat.format(this)

fun Date.toFullTimeFormat(): String = fullTimeFormat.format(this)