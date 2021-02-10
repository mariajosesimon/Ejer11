package com.example.ejer10mjose

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class Compra(val canti: Int, val producto: String, val precio: Double) : Serializable {}


class MainActivity : AppCompatActivity() {

    val productos: Map<String, Double> = mapOf(
        "Botella de vino" to 0.57,
        "Kas limon" to 0.54,
        "Kas naranja" to 0.54,
        "Red bull" to 1.25,
        "Cerveza" to 0.62,
        "Botella de vino" to 3.21
    )

    // public lateinit var addCompra: Button
    //  public lateinit var finCompra: Button

    public lateinit var cantidad: NumberPicker
    public lateinit var age: NumberPicker
    public var cant: Int = 0
    public lateinit var c: Compra
    public lateinit var prodSpinner: Spinner
    public lateinit var prods: Array<String>
    public lateinit var prod: String
    var price: Double = 0.0
    val enviarTotal: Int = 1

    //  public lateinit var fin: CompraFinalizada


    var listaCompra: MutableList<Compra> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // ----------------------------------------SPINNER DE CANTIDAD -----------------------
        //establezco los valores de la cantidad min = 0 y max a 100
        cantidad = findViewById(R.id.npCantidad)
        cantidad.setMinValue(0)
        cantidad.setMaxValue(100)

        //aqui cargamos el spinner de cantidad para que elijan cuantos productos quieren comprar.
        cantidad.displayedValues
        //aqui he creado un spinner que se pondrá en el spinner de la pantalla inicial.
        prodSpinner = findViewById(R.id.spProducto)
        prods = resources.getStringArray(R.array.productosVenta)
        val adaptadorProductos = ArrayAdapter(this, android.R.layout.simple_spinner_item, prods)

        // ----------------------------------------SPINNER DE PRODUCTOS -----------------------

        prodSpinner.adapter = adaptadorProductos
        seleccionarProductos()

        age = findViewById(R.id.npEdad)
        age.setMinValue(0)
        age.setMaxValue(100)


        findViewById<Button>(R.id.bFin).setOnClickListener {
            age = findViewById(R.id.npEdad)


            if (findViewById<EditText>(R.id.txtNombre).getText().toString().isBlank()
                || findViewById<EditText>(R.id.txtApellidos).getText().toString().isBlank()
                || age.value.toInt() == 0
                || listaCompra.size == 0
            ) {

                Toast.makeText(
                    this@MainActivity,
                    "Falta por cumplimentar datos.",
                    Toast.LENGTH_LONG
                )
                    .show()

            } else {

                println("Estoy en el else")
                /*  -----------------ESTO PERTENECE AL EJERCICIO 10. ----------------
            println(findViewById<EditText>(R.id.txtNombre).getText().toString())
            println("edad  --> " + age.value.toInt())
            fin.putExtra("nombre", findViewById<EditText>(R.id.txtNombre).getText().toString())
            // println("estosy en finalizar comprar" + intent.putExtra("nombre", findViewById<EditText>(R.id.txtNombre).getText().toString()))
            fin.putExtra(
                "apellidos",
                findViewById<EditText>(R.id.txtApellidos).getText().toString()
            )
            println(age.toString())
            fin.putExtra("edad", age.value.toString())
            fin.putExtra("lista", listaCompra.toTypedArray())*/
                var total: Double = 0.0
                for (c in listaCompra) {
                    total += (c.canti * c.precio)
                }


                val fin: Intent = Intent(this, CompraFinalizada::class.java)
                //tenemos que mandar el total de la compra
                fin.putExtra("totalCompra", total.toString())
                startActivityForResult(fin, enviarTotal)
                //startActivity(fin) //-- con esta funcion no devuelve nada.
            }


        }
    }

    fun seleccionarProductos() {

        cantidad = findViewById(R.id.npCantidad)


        //    print(cantidad.value.toInt())
        //      Toast.makeText(this@MainActivity, cantidad.value.toInt().toString(), Toast.LENGTH_LONG).show()

        prodSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@MainActivity, "No has seleccionado nada", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Toast.makeText(this@MainActivity , prods[position], Toast.LENGTH_LONG).show()

                //guardo en mis variables los datos del producto y el precio.
                prod = prods[position].toString()
                price = productos.getValue(prod)
                //Toast.makeText(this@MainActivity , price.toString(), Toast.LENGTH_LONG).show()

                /* println(prod)
                  println(price)*/

            }


        }

    }


    fun annadirProducto(view: View) {

        /* Nada de todo esto sirve para extraer el dato de int
        findViewById<View>(R.id.txtEdad).toString().toInt()
        println("1 -" + R.id.txtEdad.absoluteValue)
        println("2 -" + R.id.txtEdad.toInt())
        println("3 -" + R.id.txtEdad.toString())
        println("4 -" + R.id.txtEdad.toInt().toString())
        println("5 -" + findViewById<View>(R.id.txtEdad).toString())
       // println("6 -" + findViewById<View>(R.id.txtEdad).toString().toInt()) -- da error.
        println("7 -" + R.id.txtEdad.toString().toInt())*/

        //Realizar estos pasos para sacar la edad. extraer el dato int
        /*  age2 = findViewById<View>(R.id.txtEdad) as EditText
          var format: String
          format = age2.getText().toString()
          age = format.toInt()
          println("8 - "+ age)*/

        // Otra opcion para la edad es hacer otro pickeer numerico, como el de cantidad.


        //val cantidad: Int, val producto: String, val precio: Double

        age = findViewById(R.id.npEdad)

        if (prod.toString() == "Cerveza" || prod.toString().equals("Botella de vino")) {

            println(age)

            if (age.value.toInt() > 17) {

                cantidad.value.toInt()
                price = productos.getValue(prod)
                //Toast.makeText(this@MainActivity , price.toString(), Toast.LENGTH_LONG).show()

                /* println(prod)
             println(price)
             println( cantidad.value.toInt())*/
                c = Compra(cantidad.value.toInt(), prod, price)

                listaCompra.add(c)
                for (l in listaCompra) {
                    println("Producto ${l.producto}  precio  ${l.precio}  cantidad ${l.canti}")
                }
            } //fin if edad > 17
            else {
                Toast.makeText(this@MainActivity, "Eres menor de edad ooohhhh", Toast.LENGTH_LONG)
                    .show()
            }

        } else {

            cantidad.value.toInt()
            price = productos.getValue(prod)
            //Toast.makeText(this@MainActivity , price.toString(), Toast.LENGTH_LONG).show()

            /* println(prod)
         println(price)
         println( cantidad.value.toInt())*/
            c = Compra(cantidad.value.toInt(), prod, price)

            listaCompra.add(c)
            for (l in listaCompra) {
                println("Producto ${l.producto}  precio  ${l.precio}  cantidad ${l.canti}")
            }
        }
    }


    fun finCompra(view: View) {


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            enviarTotal -> {
                if (resultCode == Activity.RESULT_OK) {
                    // val name=data?.getStringExtra("nombre")
                    Toast.makeText(this, "Bien", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Datos mal", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}













