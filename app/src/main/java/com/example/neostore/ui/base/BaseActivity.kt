package com.example.neostore.ui.base

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.example.neostore.R
import com.example.neostore.extensions.onClick
import com.github.ybq.android.spinkit.style.ChasingDots
import kotlinx.android.synthetic.main.custom_toolbar.view.*

abstract class BaseActivity : AppCompatActivity(), BaseView {
    lateinit var customToolbar: View
    lateinit var toolbarProduct: Toolbar
    abstract var getLayout: Int
    abstract fun init()
    /*abstract var present: BasePresenter*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout)
        init()
    }

    fun initLoader() {
        val progressBar = findViewById<ProgressBar>(R.id.spin_kit)
        val chasingDots = ChasingDots()
        chasingDots.setBounds(0, 0, 100, 100)
        progressBar.indeterminateDrawable = chasingDots
    }

    fun setMyActionBar() {
        customToolbar = layoutInflater.inflate(
            R.layout.custom_toolbar, null
        ) as ConstraintLayout
        toolbarProduct = customToolbar.toolbar_product_listings
        setSupportActionBar(toolbarProduct)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    fun onToolbarBackClick() {
        customToolbar.img_back_button.onClick {
            finish()
        }
    }

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

    /* override fun onStart() {
     super.onStart()
     present.onStartView()
 }

 override fun onStop() {
     super.onStop()
     present.onStopView()
 }*/

}
