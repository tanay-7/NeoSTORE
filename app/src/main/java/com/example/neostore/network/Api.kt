package com.example.neostore.network

import com.example.neostore.ui.mvp.login.LoginResponse
import com.example.neostore.ui.mvp.registration.RegistrationResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded

import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("users/login")
    fun userLogin(@Field("email") email: String, @Field("password") password: String): Call<LoginResponse>

    @FormUrlEncoded
    @POST("users/register")
    fun userRegistration(
        @Field("first_name") first_name: String,
        @Field("last_name") last_name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("confirm_password") confirm_password: String,
        @Field("gender") gender: String,
        @Field("phone_no") phone_no: String
    ): Call<RegistrationResponse>
}

