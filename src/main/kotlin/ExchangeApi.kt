import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeApi {
    @GET("/latest")
    fun getMoney(@Query("base") base: String) : Call<ExchangeResult>
}


/*
https://api.exchangeratesapi.io/latest?base=EUR

{
"rates":
    {
    "CAD":1.5508,"HKD":9.3895,"ISK":152.9,"PHP":58.359,"DKK":7.4436,"HUF":360.1,"CZK":26.398,"AUD":1.6334,"RON":4.8728,
    "SEK":10.2358,"IDR":17134.16,"INR":89.203,"BRL":6.1906,"RUB":88.764,"HRK":7.5403,"JPY":126.18,"THB":36.415,"CHF":1.0781,
    "SGD":1.6209,"PLN":4.4663,"BGN":1.9558,"TRY":9.487,"CNY":7.9115,"NOK":10.6283,"NZD":1.722,"ZAR":18.2159,"USD":1.2114,
    "MXN":24.0683,"ILS":3.9414,"GBP":0.91143,"KRW":1314.53,"MYR":4.9328
    },

"base":"EUR",
"date":"2020-12-08"
}

 */