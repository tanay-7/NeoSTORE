package com.example.neostore

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.activity_registration.*


class RegistrationActivity : AppCompatActivity() {

    lateinit var toolbar_register: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        toolbar_register = toolbar_registration
        setSupportActionBar(toolbar_register)

        val action_bar = supportActionBar
        action_bar!!.setDisplayHomeAsUpEnabled(true)
        action_bar.setDisplayShowHomeEnabled(true)
        action_bar.setDisplayShowTitleEnabled(false)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}