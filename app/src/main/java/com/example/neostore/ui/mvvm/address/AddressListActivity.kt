package com.example.neostore.ui.mvvm.address

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.neostore.R
import com.example.neostore.extensions.onClick
import com.example.neostore.ui.base.BaseActivity
import kotlinx.android.synthetic.main.custom_toolbar.*

class AddressListActivity : BaseActivity() {
    override var getLayout = R.layout.activity_address_list
    private lateinit var mAddressViewModel: AddressViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: AddressListAdapter

    override fun init() {
        toolbarSetting()
        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        mRecyclerView = findViewById(R.id.rv_address_list)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = AddressListAdapter()
        mRecyclerView.adapter = mAdapter
    }

    private fun initViewModel() {
        //As we are providing this, The Android System will get to know that which lifecycle it has to scoped with.
        //Android System will destroy the ViewModel when Activity is finished.
        mAddressViewModel = ViewModelProviders.of(this).get(AddressViewModel::class.java)
        mAddressViewModel.getAllAddresses().observe(this,
            Observer<ArrayList<AddressEntity>> {
                //Update RecyclerView.
                mAdapter.setItems(it)
            })
    }

    private fun toolbarSetting() {
        txt_product_toolbar.text = "Address List"
        iv_back_button.onClick {
            finish()
        }
    }
}
