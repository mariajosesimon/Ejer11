package com.example.ejer10mjose

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CompraFinalizada : AppCompatActivity() {

    public lateinit var mes: NumberPicker
    public lateinit var anyo: NumberPicker


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compra)
        mes = findViewById(R.id.npFCaducidadMes)
        mes.setMinValue(1)
        mes.setMaxValue(12)

        anyo = findViewById(R.id.npFCaducidadAnio)
        anyo.setMinValue(15)
        anyo.setMaxValue(40)

        // codigo para mostrar el total en la caja del total
        var TxtViewTotal: EditText = findViewById(R.id.txtTotal)
        var mostrarTotal: Double? = getIntent().getStringExtra("totalCompra")?.toDouble()

        var total: Double
        if (mostrarTotal != null) {
            total = mostrarTotal.toDouble()
            TxtViewTotal.setText(total.toString())
            println("estoy en mostrar total")

        }

        findViewById<Button>(R.id.bValidar).setOnClickListener {
            Comprar()
        }


    }

    fun Comprar() {

        //aqui tengo que chequear que todos los datos de la tarjeta son corrctos para mandar
        // el ok, y sino mandar un toast de error

        //chequear el valor de la tarjeta es correcto
        var tarjeta = findViewById<EditText>(R.id.txtNTarjeta).getText().toString()

        if (tarjeta.length < 16) {
            Toast.makeText(this, "la tarjeta no tiene 16 digitos", Toast.LENGTH_LONG)
        }

        //chequear el valor de CVV
        var cvv = findViewById<EditText>(R.id.txtCVV).getText().toString()
        if (cvv.length < 16) {
            Toast.makeText(this, "CVV no tiene 3 digitos", Toast.LENGTH_LONG)
        }
        /*
        println("Tarjeta logitud: ")
        println(tarjeta.length)
        println("CVV long: ")
        println(cvv.length)*/

        if (tarjeta.length < 16 || cvv.length < 3
            || findViewById<EditText>(R.id.txtNombreC).getText().toString().isBlank()
            || findViewById<EditText>(R.id.txtApellidosC).getText().toString().isBlank()
        ) {
            Toast.makeText(this, "Algo no has rellenado", Toast.LENGTH_LONG)
            println("algo ha ido mal")

        } else {
            val resultadoIntencion = Intent()
            resultadoIntencion.putExtra("nombre", "perfecto")
            println("esto va bien")
            setResult(Activity.RESULT_OK, resultadoIntencion)
        }
        finish()
    }

}