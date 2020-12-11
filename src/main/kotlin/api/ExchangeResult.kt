package api

data class ExchangeResult(val rates: Rates?, val base: String?, val date: String?)

data class Rates(val CAD: Double?, val HKD: Double?, val ISK: Double?, val PHP: Double?,
            val DKK: Double?, val HUF: Double?, val CZK: Double?, val AUD: Double?, val RON: Double?,
            val SEK: Double?, val IDR: Double?, val INR: Double?, val BRL: Double?, val RUB: Double?,
            val HRK: Double?, val JPY: Double?, val THB: Double?, val CHF: Double?, val SGD: Double?,
            val PLN: Double?, val BGN: Double?, val TRY: Double?, val CNY: Double?, val NOK: Double?,
            val NZD: Double?, val ZAR: Double?, val USD: Double?, val MXN: Double?, val ILS: Double?,
            val GBP: Double?, val KRW: Double?, val MYR: Double?) {

    fun getRates(from: String, to: String): Double {
        var fromCurr = 0.0
        var toCurr = 0.0

        when (from) {
            "HUF" -> fromCurr = HUF!!
            "EUR" -> fromCurr = 1.0
            "USD" -> fromCurr = USD!!
            "CAD" -> fromCurr = CAD!!
            "AUD" -> fromCurr = AUD!!
            "RUB" -> fromCurr = RUB!!
            "CHF" -> fromCurr = CHF!!
        }

        when (to) {
            "HUF" -> toCurr = HUF!!
            "EUR" -> toCurr = 1.0
            "USD" -> toCurr = USD!!
            "CAD" -> toCurr = CAD!!
            "AUD" -> toCurr = AUD!!
            "RUB" -> toCurr = RUB!!
            "CHF" -> toCurr = CHF!!
        }

        return toCurr / fromCurr
    }
}