package com.example.neostore.network

import com.example.neostore.ui.mvp.login.LoginResponse
import com.example.neostore.ui.mvp.registration.RegistrationResponse


class APIManager {
    fun login(email: String, password: String, callback: retrofit2.Callback<LoginResponse>) {
        val apiClient = RetrofitClient.apiService.userLogin(email, password)
//        apiClient.enqueue(callback)
    }

    fun register(
        firstName: String, lastName: String, email: String,
        password: String, confirmPassword: String, gender: String,
        phoneNumber: String, callback: retrofit2.Callback<RegistrationResponse>
    ) {
        val apiClient = RetrofitClient.apiService.userRegistration(
            firstName,
            lastName,
            email,
            password,
            confirmPassword,
            gender,
            phoneNumber
        )
        apiClient.enqueue(callback)
    }
}





