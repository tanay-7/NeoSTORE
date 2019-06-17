package com.example.neostore.ui.mvp.login

interface LoginContract {
    interface LoginView {
        fun showLoginSuccess(message : String)
        fun showLoginError()
    }

    interface LoginPresenter {
        fun login(email : String,password : String)
    }
}