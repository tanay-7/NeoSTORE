package com.example.neostore.ui.mvvm.mycart

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.LayoutManager
import com.example.neostore.R
import com.example.neostore.extensions.onClick
import com.example.neostore.ui.base.BaseActivity
import kotlinx.android.synthetic.main.custom_toolbar.*

class MyCartActivity : BaseActivity() {
    override var getLayout = R.layout.activity_my_cart
    private lateinit var mViewModel: MyCartViewModel
    private lateinit var mAdapter: MyCartAdapter
    private lateinit var mRecyclerView: RecyclerView

    override fun init() {
        toolbarSetting()
        mRecyclerView = findViewById(R.id.rv_cart)
        mViewModel = ViewModelProviders.of(this).get(MyCartViewModel::class.java)
        mViewModel.init()
        mViewModel.getCartItems()?.observe(this, Observer {
            mAdapter.notifyDataSetChanged()
        })
        initRecyclerView()
    }

    private fun toolbarSetting() {
        txt_product_toolbar.text = "My Cart"
        iv_back_button.onClick {
            finish()
        }
    }

    private fun initRecyclerView() {
        mAdapter = MyCartAdapter(mViewModel.getCartItems()?.value, this)
        val linearLayoutManager: LayoutManager =
            LinearLayoutManager(this)
        mRecyclerView.layoutManager = linearLayoutManager
        mRecyclerView.adapter = mAdapter
    }
}
