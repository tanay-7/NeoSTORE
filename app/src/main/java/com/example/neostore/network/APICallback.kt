package com.example.neostore.network

import retrofit2.Call
import retrofit2.Response

open class APICallback<T>() : retrofit2.Callback<T> {
    override fun onFailure(call: Call<T>, t: Throwable) {
        onCustomResponse(200, null)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        onCustomResponse(response.code(), response.body())
    }

    open fun onCustomResponse(statusCode: Int?, response: T?) {
        print("Demo")
    }

}