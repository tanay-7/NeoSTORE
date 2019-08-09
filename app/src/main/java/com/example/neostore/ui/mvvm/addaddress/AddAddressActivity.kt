package com.example.neostore.ui.mvvm.addaddress

import com.example.neostore.R
import com.example.neostore.extensions.onClick
import com.example.neostore.ui.base.BaseActivity
import kotlinx.android.synthetic.main.custom_toolbar.*

class AddAddressActivity : BaseActivity() {
    override var getLayout = R.layout.activity_add_address

    override fun init() {
        toolbarSetting()
    }

    private fun toolbarSetting() {
        txt_product_toolbar.text = "Add Address"
        iv_back_button.onClick {
            finish()
        }
    }
}
