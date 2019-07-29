package com.example.neostore.ui.mvp.login

import android.content.Intent
import com.example.neostore.R
import com.example.neostore.extensions.onClick
import com.example.neostore.extensions.showView
import com.example.neostore.ui.base.BaseActivity
import com.example.neostore.ui.homescreen.HomeScreenActivity
import com.example.neostore.ui.mvp.registration.RegistrationActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.spinner_layout.*

class LoginActivity : BaseActivity(), LoginContract.LoginView {

    override var getLayout = R.layout.activity_login //setContentView
    // Presenter
    lateinit var loginPresenter: LoginPresenter

    override fun init() {
        loginPresenter = LoginPresenter(this)
        btn_plus.onClick {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        btn_login.onClick {
            spin_kit.showView()
            loginPresenter.login(txt_username.text.toString(), txt_password.text.toString())
        }
    }

    override fun showLoginSuccess(message: String) {
        makeToast(message)
        val intent = Intent(this, HomeScreenActivity::class.java)
        startActivity(intent)
    }

    override fun showLoginError() {
        makeToast("Login Error")
    }
}
