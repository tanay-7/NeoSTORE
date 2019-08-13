package com.example.neostore.ui.mvvm.mycart

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.neostore.R
import com.example.neostore.extensions.onClick
import com.example.neostore.ui.base.BaseActivity
import com.example.neostore.ui.mvvm.address.AddAddressActivity
import com.example.neostore.ui.mvvm.address.AddressListActivity
import com.example.neostore.ui.mvvm.address.AddressViewModel
import kotlinx.android.synthetic.main.activity_my_cart.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class MyCartActivity : BaseActivity() {
    override var getLayout = R.layout.activity_my_cart
    private lateinit var mViewModel: MyCartViewModel
    private lateinit var mAdapter: MyCartAdapter
    private lateinit var mRecyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var mAddressViewModel: AddressViewModel

    override fun init() {
        toolbarSetting()
        mRecyclerView = findViewById(R.id.rv_cart)
        mViewModel = ViewModelProviders.of(this).get(MyCartViewModel::class.java)
        mViewModel.init()
        mViewModel.getCartItems()?.observe(this, Observer {
            mAdapter.notifyDataSetChanged()
        })
        initRecyclerView()
        onOrderNowClick()
    }

    private fun onOrderNowClick() {
        btn_order_now.onClick {
            mAddressViewModel = ViewModelProviders.of(this).get(AddressViewModel::class.java)
            mAddressViewModel.getAllAddresses().observe(this, Observer {
                if (it.isNullOrEmpty()) {
                    val intent = Intent(this@MyCartActivity, AddAddressActivity::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this@MyCartActivity, AddressListActivity::class.java)
                    startActivity(intent)
                }
            })
        }
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
            androidx.recyclerview.widget.LinearLayoutManager(this)
        mRecyclerView.layoutManager = linearLayoutManager
        mRecyclerView.adapter = mAdapter
    }
}
