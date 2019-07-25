package com.example.neostore.network

import com.example.neostore.utilities.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    init {
        buildRetrofit()
    }

    companion object {
        private var logging = HttpLoggingInterceptor()
        private var httpClient = OkHttpClient.Builder().addInterceptor(logging)
        private lateinit var retroClient: Retrofit
        private var retrofitClient: RetrofitClient? = null

        fun getInstance(): RetrofitClient {
            if (retrofitClient == null) {
                retrofitClient = RetrofitClient()
            }
            return retrofitClient!!
        }
    }

    private fun buildRetrofit(): Api {
        retroClient = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()

        return retroClient.create(Api::class.java)
    }
}