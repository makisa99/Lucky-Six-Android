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
        //  binding.btnReset.setOnClickListener {
        GlobalScope.launch {
            while (true) {
                kolo++
                setText(binding.tvKolo, kolo.toString())
                setText(binding.tvDrugih15, "")
                setText(binding.tvPrvih15, "")
                setText(binding.tvPrvih5, "")
                setText(binding.tvGlavniBroj, "Ceka se nova igra")
                max = 150
                lista.clear()
                delay(5000L)
                for (i in 1..35) {
                    var rnds = (1..48).random()
                    while (lista.contains(rnds)) {
                        rnds = (1..48).random()
                    }
                    lista.add(rnds)
                    println(lista)
                    //  var tekstI = "%02d".format(i)
                    var tekstrnds = "%02d".format(rnds)
                    if (i < 6) {
                        val tekstIzPrvih5 = binding.tvPrvih5.text.toString()
                        //   Log.d("Tekst iz prvih 15", tekstIzPrvih15)
                        // val zaPrikaz15 = "$tekstIzPrvih15 $tekstI. $tekstrnds\n"
                        val zaPrikaz5 = "$tekstIzPrvih5 $tekstrnds "
                        setText(binding.tvPrvih5, zaPrikaz5)
                    } else if (i < 21) {
                        //         Log.d("Tekst pre", binding.tvPrvih15.text.toString())
                        val tekstIzPrvih15 = binding.tvPrvih15.text.toString()
                        //   Log.d("Tekst iz prvih 15", tekstIzPrvih15)
                        // val zaPrikaz15 = "$tekstIzPrvih15 $tekstI. $tekstrnds\n"
                        val zaPrikaz15 = "$tekstIzPrvih15$tekstrnds - SVE\n"
                        setText(binding.tvPrvih15, zaPrikaz15)
                    } else {
                        val tekstIzDrugih15 = binding.tvDrugih15.text.toString()
                        // val zaPrikaz30 = "$tekstIzDrugih15 $tekstI. $tekstrnds - $max\n"
                        val zaPrikaz30 = "$tekstIzDrugih15$tekstrnds - $max\n"
                        max -= 10
                        setText(binding.tvDrugih15, zaPrikaz30)
                    }
                    setText(binding.tvGlavniBroj, rnds.toString())
                    delay(4000L)
                }
                /*  val thread = Thread {
                      try {
                          rawJSON(kolo.toString(), lista.toString())
                      } catch (e: Exception) {
                          e.printStackTrace()
                      }
                  }
                  thread.start()*/
                /*    if (isOnline(this)) {
                        thread.start()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Nemate pristup internetu za POST na sajt",
                            Toast.LENGTH_LONG
                        ).show()
                    }*/
                //delay(15000L)
                for (i in 60 downTo 1) {
                    setText(binding.tvGlavniBroj, i.toString())
                    delay(1000L)
                }

            }
        }
        //  }
        /*
        binding.tvPrvih15.text = ""
        binding.tvDrugih15.text = ""
        binding.tvGlavniBroj.text = "Ceka se nova igra"
        max = 150
        lista.clear()
        for (i in 1..30) {
            var rnds = (1..48).random()
            while (lista.contains(rnds)) {
                rnds = (1..48).random()
            }
            lista.add(rnds)
            println(lista)
            //  var tekstI = "%02d".format(i)
            var tekstrnds = "%02d".format(rnds)
            if (i < 16) {
                val tekstIzPrvih15 = binding.tvPrvih15.text.toString()
                //  val zaPrikaz15 = "$tekstIzPrvih15 $tekstI. $tekstrnds\n"
                val zaPrikaz15 = "$tekstIzPrvih15$tekstrnds - SVE\n"
                binding.tvPrvih15.text = zaPrikaz15
            } else {
                val tekstIzDrugih15 = binding.tvDrugih15.text.toString()
                // val zaPrikaz30 = "$tekstIzDrugih15 $tekstI. $tekstrnds - $max\n"
                val zaPrikaz30 = "$tekstIzDrugih15$tekstrnds - $max\n"
                max -= 10
                binding.tvDrugih15.text = zaPrikaz30
            }
            binding.tvGlavniBroj.text = rnds.toString()
            //Cekaj 4 sec
        }*/
    }

    private fun setText(text: TextView, value: String) {
        runOnUiThread { text.text = value }
    }

  /*  private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

    private fun rawJSON(kolo: String, brojevi: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://mferketic.com/")
            .build()
        val service = retrofit.create(ApiRest::class.java)
        val jsonObject = JSONObject()
        jsonObject.put("kolo", kolo)
        jsonObject.put("brojevi", brojevi)
        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
        CoroutineScope(Dispatchers.IO).launch {
            service.rawJSON(requestBody)
        }
    }*/
}