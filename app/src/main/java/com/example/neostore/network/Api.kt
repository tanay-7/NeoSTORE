package com.example.neostore.network

import com.example.neostore.ui.mvp.login.LoginResponse
import com.example.neostore.ui.mvp.productdetails.ProductDetailsResponse
import com.example.neostore.ui.mvp.productlisting.ProductListResponse
import com.example.neostore.ui.mvp.registration.RegistrationResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @FormUrlEncoded
    @POST("users/login")
    fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Observable<LoginResponse>

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
    ): Call<RegistrationResponse> //  Call

    @GET("products/getList")
    fun getProductList(
        @Query("product_category_id") product_category_id: String,
        @Query("limit") limit: String,
        @Query("page") page: String
    ): Observable<ProductListResponse>

    @GET("products/getDetail")
    fun getProductDetails(
        @Query("product_id") product_id: Number
    ): Observable<ProductDetailsResponse>
}

