package com.example.neostore.ui.mvp.login

import android.util.Log
import com.example.neostore.network.Api
import com.example.neostore.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter : LoginContract.LoginPresenter {

    lateinit var apiService: Api
    var loginView: LoginContract.LoginView

    constructor(loginView: LoginContract.LoginView) {
        this.loginView = loginView
    }

    override fun login(email: String, password: String) {
        // Api Call
        apiService = RetrofitClient.provideRetro().create(Api::class.java)
        apiService.userLogin(email, password).enqueue(
            object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                    Log.d("Error", t.toString())
                    loginView.showLoginError()
                }

                override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        var results = response.body()
                        loginView.showLoginSuccess(results!!.message)
                    }
                }
            }
        )
    }
}