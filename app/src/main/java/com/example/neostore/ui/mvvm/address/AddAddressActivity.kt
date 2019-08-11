package com.example.neostore.ui.mvvm.address

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import com.example.neostore.R
import com.example.neostore.extensions.onClick
import com.example.neostore.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_add_address.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class AddAddressActivity : BaseActivity() {
    override var getLayout = R.layout.activity_add_address
    private lateinit var mAddressViewModel: AddressViewModel

    override fun init() {
        toolbarSetting()
        //initViewModel()
        onSaveAddressBtnClick()
    }

    private fun onSaveAddressBtnClick() {
        btn_save_address.onClick {
            val intent = Intent(this, AddressListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initViewModel() {
        //As we are providing this, The Android System will get to know that which lifecycle it has to scoped with.
        //Android System will destroy the ViewModel when Activity is finished.
        mAddressViewModel = ViewModelProviders.of(this).get(AddressViewModel::class.java)
        mAddressViewModel.getAllAddresses().observe(this,
            Observer<ArrayList<AddressEntity>> {
                //Update RecyclerView.
            })
    }

    private fun toolbarSetting() {
        txt_product_toolbar.text = "Add Address"
        iv_back_button.onClick {
            finish()
        }
    }
}
