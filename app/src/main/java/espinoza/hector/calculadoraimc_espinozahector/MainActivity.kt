package espinoza.hector.calculadoraimc_espinozahector

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    var peso: Float = 0f
    var estatura: Float = 0f
    var imc: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Referencias a los elementos de la UI
        val calcularIMC: Button = findViewById(R.id.btnCalcular)
        val etPeso: EditText = findViewById(R.id.weight)
        val etEstatura: EditText = findViewById(R.id.height)
        val txtIMC: TextView = findViewById(R.id.imc)
        val range: TextView = findViewById(R.id.range)


        // Evento del botón
        calcularIMC.setOnClickListener {
            // Convertir valores ingresados y validar que no estén vacíos
            peso = etPeso.text.toString().toFloatOrNull()!!
            estatura = etEstatura.text.toString().toFloatOrNull()!!

            calcularIMC()

            if (peso != null && estatura != null && estatura > 0) {
                txtIMC.text = "Tu IMC es de %.4f".format(imc)

                //Veirifica en que rango de peso se esta y se le asigna color en base a ello
                var (color, rangoPeso) = when (imc) {
                    in 0.0..18.5 -> Pair(R.color.colorYellow, "Bajo Peso")
                    in 18.5..24.9 -> Pair(R.color.colorGreen, "Peso Normal")
                    in 25.0..29.9 -> Pair(R.color.colorGreenish, "Sobrepeso")
                    in 30.0..34.9 -> Pair(R.color.colorOrange, "Obesidad grado 1")
                    in 35.0..39.9 -> Pair(R.color.colorBrown, "Obesidad grado 2")
                    else -> Pair(R.color.colorRed, "Obesidad grado 3")
                }
                range.setBackgroundColor(ContextCompat.getColor(this, color))
                range.setText(rangoPeso)


            } else {
                txtIMC.text = "Por favor ingrese datos válidos"
            }
        }
    }

    // Función para calcular el IMC
    fun calcularIMC() {
        if (estatura > 0) {
            imc = (peso / (estatura*estatura))*10000
        } else {
            imc = 0f
        }
    }


}