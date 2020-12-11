import util.getResource
import api.ExchangeApi
import api.ExchangeResult
import javafx.application.Application
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.layout.*
import javafx.scene.text.Font
import javafx.stage.Stage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import util.round

class Exchanger : Application() {
    companion object {
        private const val WIDTH = 512
        private const val HEIGHT = 512
    }

    private lateinit var mainScene: Scene
    private lateinit var graphicsContext: GraphicsContext
    private lateinit var background: Image

    private var fromTextField = TextField()
    private var choiceFrom = ChoiceBox<String>()
    private var choiceTo = ChoiceBox<String>()
    private val exchangeButton = Button("Exchange")
    private val resultLabel = Label("")

    val retrofit = Retrofit.Builder()
                .baseUrl("http://api.exchangeratesapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    val api = retrofit.create(ExchangeApi::class.java)

    override fun start(mainStage: Stage) {
        createUI(mainStage)

        exchange()

        mainStage.show()
    }

    private fun createUI(mainStage: Stage) {
        val fromLabel = Label("From:")
        val toLabel = Label("To:")

        mainStage.title = "Currency Exchanger"
        mainStage.isResizable = false

        fromLabel.font = Font.font(20.0)
        fromTextField.font = Font.font(20.0)
        val fromCurrency = HBox(20.0, fromLabel, fromTextField, choiceFrom)
        fromCurrency.alignment = Pos.CENTER

        toLabel.font = Font.font(20.0)
        val toCurrency = HBox(20.0, toLabel, choiceTo)
        toCurrency.alignment = Pos.CENTER

        exchangeButton.font = Font.font(20.0)

        resultLabel.font = Font.font(40.0)
        resultLabel.alignment = Pos.CENTER

        val form = VBox(50.0, fromCurrency, toCurrency, exchangeButton, resultLabel)
        form.alignment = Pos.CENTER

        val root = Group()
        mainScene = Scene(form, 512.0, 512.0)
        mainStage.scene = mainScene
        val canvas = Canvas(WIDTH.toDouble(), HEIGHT.toDouble())

        background = Image(getResource("/money.png"))
        val bgImage = BackgroundImage(
                background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT
        )
        val bgFinal = Background(bgImage)
        form.background = bgFinal
        root.children.add(canvas)

        fillWithCurrencies()
    }

    private fun exchange() {
        exchangeButton.onAction = EventHandler {
            val fromCurrency = choiceFrom.value
            val call = api.getMoney(fromCurrency)

            call.enqueue(object : Callback<ExchangeResult> {
                override fun onFailure(call: Call<ExchangeResult>, t: Throwable) {
                    Platform.runLater {
                        resultLabel.text = t.message
                    }
                }

                override fun onResponse(call: Call<ExchangeResult>, response: Response<ExchangeResult>) {
                    val result = response.body()
                    Platform.runLater {
                        var rate = result?.rates?.getRates(choiceFrom.value, choiceTo.value)
                        if (rate != null) {
                            if (choiceTo.value == "EUR")
                                rate /= 1.0
                        }
                        println(rate)
                        resultLabel.text =
                            "${round(fromTextField.characters.toString().toDouble() * rate!!)} ${choiceTo.value}"
                    }
                }
            })
        }
    }

    private fun fillWithCurrencies() {
        choiceFrom.items.add("HUF")
        choiceFrom.items.add("EUR")
        choiceFrom.items.add("USD")
        choiceFrom.items.add("CAD")
        choiceFrom.items.add("AUD")
        choiceFrom.items.add("RUB")
        choiceFrom.items.add("CHF")

        choiceFrom.value = "EUR"

        choiceTo.items.add("HUF")
        choiceTo.items.add("EUR")
        choiceTo.items.add("USD")
        choiceTo.items.add("CAD")
        choiceTo.items.add("AUD")
        choiceTo.items.add("RUB")
        choiceTo.items.add("CHF")

        choiceTo.value = "HUF"
    }
}