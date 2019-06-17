package com.example.neostore.ui.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

abstract class BaseActivity : AppCompatActivity() {

    abstract var getLayout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout)
    }

    fun makeToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
