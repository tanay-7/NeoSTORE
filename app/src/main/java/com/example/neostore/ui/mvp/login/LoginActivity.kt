package com.example.neostore.ui.mvp.login

import android.content.Intent
import android.os.Bundle
import com.example.neostore.ui.homescreen.HomeScreenActivity
import com.example.neostore.ui.base.BaseActivity
import com.example.neostore.R
import com.example.neostore.ui.mvp.registration.RegistrationActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginContract.LoginView {

    override var getLayout = R.layout.activity_login //setContentView

    // Presenter
    lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            init()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun init() {
        loginPresenter = LoginPresenter(this)
        btn_plus.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener {
            loginPresenter?.login(etxt_username.text.toString(), etxt_password.text.toString())
        }
    }

    override fun showLoginSuccess(message: String) {
        makeToast(message)
        var intent = Intent(this, HomeScreenActivity::class.java)
        startActivity(intent)
    }

    override fun showLoginError() {
        makeToast("Login Error")
    }
}
