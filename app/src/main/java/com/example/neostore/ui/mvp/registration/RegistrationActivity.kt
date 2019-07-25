package com.example.neostore.ui.mvp.registration

import android.content.Intent
import android.support.v7.widget.Toolbar
import com.example.neostore.R
import com.example.neostore.ui.base.BaseActivity
import com.example.neostore.ui.mvp.login.LoginActivity
import com.example.neostore.extensions.onClick
import kotlinx.android.synthetic.main.activity_registration.*


class RegistrationActivity : BaseActivity(), RegistrationContract.RegistartionView {
    lateinit var toolbar_register: Toolbar
    lateinit var registrationPresenter: RegistrationPresenter
    override var getLayout = R.layout.activity_registration //setContentView

    override fun init() {
        registrationPresenter = RegistrationPresenter(this)
        var gender: String = ""
        toolbar_register = toolbar_registration
        setSupportActionBar(toolbar_register)

        val action_bar = supportActionBar
        action_bar!!.setDisplayHomeAsUpEnabled(true)
        action_bar.setDisplayShowHomeEnabled(true)
        action_bar.setDisplayShowTitleEnabled(false)

        rdgrp.setOnCheckedChangeListener { group, checkedId ->
            gender = if (checkedId == R.id.rdbtn_male) "Male" else "Female"
        }

        btn_register.onClick {
            registrationPresenter.register(
                txt_firstname.text.toString(),
                txt_lastname.text.toString(),
                txt_email.text.toString(),
                txt_password.text.toString(),
                txt_confirmpassword.text.toString(),
                gender,
                txt_phone.text.toString()
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun showRegistrationSuccess(message: String) {
        makeToast(message)
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun showRegistrationError(err_message: String) {
        makeToast(err_message)
    }
}