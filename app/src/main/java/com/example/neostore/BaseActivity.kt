package com.example.neostore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

abstract class BaseActivity : AppCompatActivity() {

    abstract var getLayout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout)
    }
}
