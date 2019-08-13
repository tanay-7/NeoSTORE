package com.example.neostore.ui.mvp.login

import android.content.Intent
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.neostore.R
import com.example.neostore.extensions.onClick
import com.example.neostore.extensions.showView
import com.example.neostore.ui.base.BaseActivity
import com.example.neostore.ui.homescreen.HomeScreenActivity
import com.example.neostore.ui.mvp.registration.RegistrationActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.custom_popup.view.*
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

        onLoginWithAnotherOptions()
    }

    private fun onLoginWithAnotherOptions() {
        cv_login_with_another_options.onClick {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_popup, null)

            val mBuilder = AlertDialog.Builder(this)
            mBuilder.setView(R.layout.custom_popup)

            val mAlertDialog = mBuilder.show()

            mDialogView.tv_close.onClick {
                mAlertDialog.dismiss()
            }
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
