package com.example.numerossorteados2

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val editQuantNumeros: EditText = findViewById(R.id.editQuantNumeros)
        val txtResultado: TextView = findViewById(R.id.txtResultado)
        val btnSorteio: Button = findViewById(R.id.btnSorteio)

        prefs = getSharedPreferences("db", Context.MODE_PRIVATE)
        val result = prefs.getString("result", null)
        if (result != null){
            txtResultado.text = "Ultimo Sorteio: $result"
        }

        btnSorteio.setOnClickListener {

            val text = editQuantNumeros.text.toString()

            numeros(text, txtResultado)

        }

    }

    private fun numeros(text: String, txtResultado: TextView) {

        if (text.isEmpty()) {
            Toast.makeText(this, "Digite um número entre 6 e 15", Toast.LENGTH_LONG).show()
            return
        }

        val qtd = text.toInt()

        if (qtd < 6 || qtd > 15) {
            Toast.makeText(this, "Digite um número entre 6 e 15", Toast.LENGTH_LONG).show()
            return
        }

        val num = mutableSetOf<Int>()
        val random = Random()

        while (true) {
            val numero = random.nextInt(60)
            num.add(numero + 1)

            if (num.size == qtd) {
                break
            }

        }

        txtResultado.text = num.joinToString(" - ")

        val editor = prefs.edit()
        editor.putString("result", txtResultado.text.toString())
        editor.apply()

    }
}


