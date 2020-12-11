package util

import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.concurrent.Exchanger

fun getResource(filename: String): String {
    return Exchanger::class.java.getResource(filename).toString()
}

fun round(number: Double): Double {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.CEILING
    return df.format(number).toDouble()
}