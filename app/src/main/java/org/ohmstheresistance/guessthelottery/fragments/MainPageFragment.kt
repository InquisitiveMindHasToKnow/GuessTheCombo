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
import org.ohmstheresistance.guessthelottery.databinding.MainPageFragmentBinding
import java.io.IOException

class MainPageFragment : Fragment() {

    lateinit var mainPageFragmentBinding: MainPageFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainPageFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.main_page_fragment, container, false)

      getTheDaysCombo()

        return mainPageFragmentBinding.root
    }

    private fun getTheDaysCombo() {
        val okHttpClient = OkHttpClient()

        val baseOfNumbers = "10"
        val col = "3"
        val num = "3"
        val minNum = "0"
        val maxNum = "9"
        val format = "plain"
        val rnd = "new"

        val url = "https://www.random.org/integers/?num=" + num + "&min=" + minNum + "&max=" + maxNum + "&col=" + col + "&base=" + baseOfNumbers + "&format=" + format + "&rnd=" + rnd
        val request = Request.Builder()
            .url(url)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(context, "Failed to get numbers", Toast.LENGTH_LONG).show()

            }

            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful){

                    var guessedCombo = response.body?.string()
                    Log.e("GuessedCombo", guessedCombo.toString())

                    MainScope().launch {
                        withContext(Dispatchers.Default) {

                        }
                        mainPageFragmentBinding.displayComboTextview.text = "Today's combo will be " + guessedCombo
                    }
                }

            }
        })

    }
}