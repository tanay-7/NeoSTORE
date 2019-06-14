package com.example.neostore.features.registration

import com.example.neostore.network.Api
import com.example.neostore.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import org.json.JSONObject


class RegistrationPresenter : RegistrationContract.RegistrationPresenter {

    lateinit var apiService: Api
    var registrationView: RegistrationContract.RegistartionView

    constructor(registrationView: RegistrationContract.RegistartionView) {
        this.registrationView = registrationView
    }

    override fun register(
        first_name: String,
        last_name: String,
        email: String,
        password: String,
        confirm_password: String,
        gender: String,
        phone_no: String
    ) {
        apiService = RetrofitClient.provideRetro().create(Api::class.java)
        apiService.userRegistration(first_name, last_name, email, password, confirm_password, gender, phone_no)
            .enqueue(object : Callback<RegistrationResponse> {
                override fun onResponse(call: Call<RegistrationResponse>, response: Response<RegistrationResponse>) {
                    if (response.isSuccessful) {
                        var results = response.body()
                        registrationView.showRegistrationSuccess("Welcome ${results!!.data.first_name}")

                    } else {
                        val jObjError = JSONObject(response.errorBody()?.string())
                        registrationView.showRegistrationError("${jObjError.get("user_msg")}")
                    }
                }

                override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                    registrationView.showRegistrationError(t.message.toString())
                }
            })
    }
}