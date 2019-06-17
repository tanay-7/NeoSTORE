package com.example.neostore.network

import com.example.neostore.BuildConfig
import com.example.neostore.utilities.Utils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        internal var logging = HttpLoggingInterceptor()
        internal var httpClient = OkHttpClient.Builder().addInterceptor(logging)

        fun provideRetro(): Retrofit {

            if (BuildConfig.DEBUG)
                logging.setLevel(HttpLoggingInterceptor.Level.BODY) else
                logging.setLevel(HttpLoggingInterceptor.Level.NONE)

            return Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
        }
    }
}