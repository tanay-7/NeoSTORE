package com.example.neostore.ui.mvvm.address

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
        onSaveAddressBtnClick()
    }

    private fun onSaveAddressBtnClick() {
        btn_save_address.onClick {
            val obj = AddressEntity(
                address = et_address.text.toString(),
                landmark = et_landmark.text.toString(),
                city = et_city.text.toString(),
                state = et_state.text.toString(),
                country = et_country.text.toString(),
                zipCode = et_zipcode.text.toString()
            )

            mAddressViewModel = ViewModelProviders.of(this).get(AddressViewModel::class.java)
            //Inserting address to DB.
            mAddressViewModel.insert(obj)

            val intent = Intent(this, AddressListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun toolbarSetting() {
        txt_product_toolbar.text = "Add Address"
        iv_back_button.onClick {
            finish()
        }
    }
}
