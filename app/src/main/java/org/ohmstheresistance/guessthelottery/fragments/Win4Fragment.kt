package org.ohmstheresistance.guessthelottery.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import org.ohmstheresistance.guessthelottery.R
import org.ohmstheresistance.guessthelottery.databinding.Win4FragmentBinding
import java.io.IOException

class Win4Fragment : Fragment() {

    lateinit var win4FragmentBinding: Win4FragmentBinding
    lateinit var win4GuessedCombo: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        win4FragmentBinding = DataBindingUtil.inflate(inflater, R.layout.win4_fragment, container, false)

        getWin4ComboViaButtonClick()

        return win4FragmentBinding.root

    }

    private fun getWin4ComboFromApi() {
        val okHttpClient = OkHttpClient()

        val baseOfNumbers = "10"
        val col = "4"
        val num = "4"
        val minNum = "0"
        val maxNum = "9"
        val format = "plain"
        val rnd = "new"

        val url =
            "https://www.random.org/integers/?num=" + num + "&min=" + minNum + "&max=" + maxNum + "&col=" + col + "&base=" + baseOfNumbers + "&format=" + format + "&rnd=" + rnd
        val request = Request.Builder()
            .url(url)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                MainScope().launch {
                    withContext(Dispatchers.Default) {

                    }
                    Toast.makeText(context, "Failed to get numbers", Toast.LENGTH_LONG).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {

                    win4GuessedCombo = response.body?.string().toString()
                    Log.e("GuessedCombo", win4GuessedCombo)

                    MainScope().launch {
                        withContext(Dispatchers.Default) {

                        }
                        win4FragmentBinding.comboTextview.text = win4GuessedCombo
                    }
                }
            }
        })
    }
    private fun getWin4ComboViaButtonClick(){
        win4FragmentBinding.nyNumbersLogoImageview.setOnClickListener {
            getWin4ComboFromApi()
        }
    }
}