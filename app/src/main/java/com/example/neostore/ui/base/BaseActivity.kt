package com.example.neostore.ui.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

abstract class BaseActivity : AppCompatActivity(), BaseView {

    abstract var getLayout: Int
    abstract fun init()
    /*abstract var present: BasePresenter*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout)
        init()
    }

    /* override fun onStart() {
         super.onStart()
         present.onStartView()
     }

     override fun onStop() {
         super.onStop()
         present.onStopView()
     }*/

    fun makeToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun initPresenter() {

    }

    override fun showLoader() {

    }

    override fun closeLoader() {

    }

    override fun showError() {

    }
}
