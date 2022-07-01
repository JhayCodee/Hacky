package com.hawk.hacky.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hawk.hacky.api.APIRequest
import com.hawk.hacky.databinding.FragmentNewsBinding
import com.hawk.hacky.utils.Constants.Companion.BASE_URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding: FragmentNewsBinding get() =_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewsBinding.inflate(inflater)
        return binding.root

        makeAPIRequest()

    }

    fun makeAPIRequest() {

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIRequest::class.java)

        GlobalScope.launch(Dispatchers.IO) {

            try {
                val response = api.getNews()

                for (article in response.news) {
                    Log.i("MainActivity", "Result = $article")
                    println(article.toString())
                }

            } catch (e: Exception) {
                Log.e("MainActivity", e.toString())
                println(e.toString())
            }
        }

    }

}