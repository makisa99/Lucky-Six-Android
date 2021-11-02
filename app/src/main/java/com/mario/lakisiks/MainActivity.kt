package com.mario.lakisiks

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mario.lakisiks.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Retrofit


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var max: Int
        var kolo = 0
        val lista = arrayListOf<Int>()
        GlobalScope.launch {
            while (true) {
                kolo++
                setText(binding.tvKolo, kolo.toString())
                setText(binding.tvDrugih15, "")
                setText(binding.tvPrvih15, "")
                setText(binding.tvPrvih5, "")
                setText(binding.tvGlavniBroj, "Ceka se nova igra")
                max = 150
                setText(binding.tvSortirano, "")
                lista.clear()
                delay(2000L)
                for (i in 1..35) {
                    var rnds = (1..48).random()
                    while (lista.contains(rnds)) {
                        rnds = (1..48).random()
                    }
                    lista.add(rnds)
                    var tekstrnds = "%02d".format(rnds)
                    if (i < 6) {
                        val tekstIzPrvih5 = binding.tvPrvih5.text.toString()
                        val zaPrikaz5 = "$tekstIzPrvih5 $tekstrnds "
                        setText(binding.tvPrvih5, zaPrikaz5)
                    } else if (i < 21) {
                        val tekstIzPrvih15 = binding.tvPrvih15.text.toString()
                        val zaPrikaz15 = "$tekstIzPrvih15$tekstrnds - SVE\n"
                        setText(binding.tvPrvih15, zaPrikaz15)
                    } else {
                        val tekstIzDrugih15 = binding.tvDrugih15.text.toString()
                        val zaPrikaz30 = "$tekstIzDrugih15$tekstrnds - $max\n"
                        max -= 10
                        setText(binding.tvDrugih15, zaPrikaz30)
                    }
                    setText(binding.tvGlavniBroj, rnds.toString())
                    delay(2500L)
                }
                lista.sort();
                setText(binding.tvSortirano, lista.toString())
                for (i in 45 downTo 1) {
                    setText(binding.tvGlavniBroj, i.toString())
                    delay(1000L)
                }
            }
        }
    }

    private fun setText(text: TextView, value: String) {
        runOnUiThread { text.text = value }
    }
}