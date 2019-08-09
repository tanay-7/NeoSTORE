package com.example.neostore.ui.mvvm.addresslist

import com.example.neostore.R
import com.example.neostore.extensions.onClick
import com.example.neostore.ui.base.BaseActivity
import kotlinx.android.synthetic.main.custom_toolbar.*

class AddressListActivity : BaseActivity() {
    override var getLayout = R.layout.activity_address_list
    override fun init() {
        toolbarSetting()
    }

    private fun toolbarSetting() {
        txt_product_toolbar.text = "Address List"
        iv_back_button.onClick {
            finish()
        }
    }
}
