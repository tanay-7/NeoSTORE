package com.example.neostore.features.login

interface LoginContract {
    interface LoginView {
        fun showLoginSuccess(message : String)
        fun showLoginError()
        fun showLoader()
        fun closeLoader()
    }

    interface LoginPresenter {
        fun login(email : String,password : String)
    }
}