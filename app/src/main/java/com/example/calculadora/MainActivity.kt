package com.example.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import java.text.DecimalFormat
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

class MainActivity : AppCompatActivity() {
    val SUMA = "+"
    val RESTA = "-"
    val MULTIPLICACION = "*"
    val DIVISION = "/"
    val PORCENTAJE = "%"

    var operacionActual = ""

    var Num1:Double = Double.NaN
    var Num2:Double = Double.NaN

    lateinit var tvTemp:TextView
    lateinit var tvResult:TextView

    lateinit var formatoDecimal:DecimalFormat



    override fun onCreate(savedInstanceState: Bundle?) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        formatoDecimal = DecimalFormat("#.##########")
        tvTemp= findViewById(R.id.tvTemp)
        tvResult= findViewById(R.id.tvResult)

    }
    fun cambiarOperador(b:View){
        if(tvTemp.text.toString().isNotEmpty() || Num1.toString()!= "NaN") {
            calcular()
            val boton:Button = b as Button
            if(boton.text.toString().trim()=="÷"){
                operacionActual = "/"
            }else if(boton.text.toString().trim()=="X"){
                operacionActual = "x"
            }else {
                operacionActual = boton.text.toString().trim()
            }
            if (tvTemp.text.toString().isEmpty()){
                tvTemp.text=tvResult.text
            }

            tvResult.text = formatoDecimal.format(Num1) + operacionActual
            tvTemp.text = ""
        }

    }

    fun calcular() {
        try {
            if (Num1.toString() != "NaN"){
                if(tvTemp.text.toString().isEmpty()) {
                    tvTemp.text = tvResult.text.toString()
                }
                Num2 = tvTemp.text.toString().toDouble()
                tvTemp.text = ""

                when(operacionActual){
                    "+" -> Num1 += Num2
                    "-" -> Num1 -= Num2
                    "/" -> Num1 /= Num2
                    "x" -> Num1 *= Num2
                    "^" -> Num1 = Num1.pow(Num2)
                    "Sin" -> Num1 = sin(Math.toRadians(Num1))
                    "Cos" -> Num1 = cos(Math.toRadians(Num1))
                    "Tan" -> Num1 = tan(Math.toRadians(Num1))

                }
            }else {
                Num1 = tvTemp.text.toString().toDouble()

            }
        }catch (e: Exception) {

        }

    }
    fun trigonometria(b: View) {
        try {
            val boton: Button = b as Button
            val numero = tvTemp.text.toString().toDouble()
            val resultado = when (boton.text.toString().trim()) {
                "Sin" -> sin(Math.toRadians(numero))
                "Cos" -> cos(Math.toRadians(numero))
                "Tan" -> tan(Math.toRadians(numero))
                "√" -> sqrt(numero)

                else -> numero
            }
            tvResult.text = formatoDecimal.format(resultado)
            tvTemp.text = ""
        } catch (e: Exception) {
        }
    }

    fun factorial(b: View) {
        try {
            val numero = tvTemp.text.toString().toInt()
            val resultado = factorial(numero)
            tvResult.text = resultado.toString()
            tvTemp.text = ""
        } catch (e: Exception) {
        }
    }
    fun porcentaje(b: View) {
        try {
            val numero = tvTemp.text.toString().toDouble()
            val resultado = numero/100
            tvResult.text = resultado.toString()
            tvTemp.text = ""
        } catch (e: Exception) {
        }
    }
    private fun factorial(n: Int): Long {
        return if (n <= 1) 1 else n * factorial(n - 1)
    }

    fun selectNum (b: View){
        val boton:Button = b as Button
        //if(tvTemp.text.toString()== "0"){
        //    tvTemp.text= " "
        //}
        tvTemp.text = tvTemp.text.toString() + boton.text.toString()
    }

    fun igual (b: View){
        calcular()
        tvResult.text = formatoDecimal.format(Num1)
        //Num1 = Double.NaN
        operacionActual = ""
    }
  
    fun borrar(b:View){
        val boton:Button = b as Button
        if(boton.text.toString().trim()=="C"){
            //if(tvTemp.text.toString().length>0)
            if(tvTemp.text.toString().isNotEmpty()){
                var datosActuales:CharSequence = tvTemp.text.toString()
                tvTemp.text = datosActuales.subSequence(0,datosActuales.length-1)
            }else{
                Num1 = Double.NaN
                Num2 = Double.NaN
                tvTemp.text= ""
                tvResult.text = ""
            }
        }else if(boton.text.toString().trim()=="CA"){
            Num1 = Double.NaN
            Num2 = Double.NaN
            tvTemp.text= ""
            tvResult.text = ""
        }

    }
}