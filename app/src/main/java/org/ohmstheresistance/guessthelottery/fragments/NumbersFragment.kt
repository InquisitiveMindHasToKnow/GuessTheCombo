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
import org.ohmstheresistance.guessthelottery.databinding.NumbersFragmentBinding
import java.io.IOException

class NumbersFragment : Fragment() {

    lateinit var numbersFragmentBinding: NumbersFragmentBinding
    lateinit var guessedCombo: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       numbersFragmentBinding =  DataBindingUtil.inflate(inflater, R.layout.numbers_fragment, container, false)

        getNewComboViaButtonClick()

        return numbersFragmentBinding.root
    }

    private fun getTheDaysComboFromApi() {
        val okHttpClient = OkHttpClient()

        val baseOfNumbers = "10"
        val col = "3"
        val num = "3"
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

                    guessedCombo = response.body?.string().toString()
                    Log.e("GuessedCombo", guessedCombo.toString())

                    MainScope().launch {
                        withContext(Dispatchers.Default) {

                        }
                        numbersFragmentBinding.comboTextview.text = guessedCombo
                    }
                }
            }
        })
    }
    private fun getNewComboViaButtonClick(){
        numbersFragmentBinding.nyNumbersLogoImageview.setOnClickListener {
            getTheDaysComboFromApi()
        }
    }
}