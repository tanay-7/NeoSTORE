package com.example.neostore.ui.mvp.productdetails

import com.example.neostore.R
import com.example.neostore.ui.base.BaseActivity

class ProductDetailsActivity : BaseActivity(), ProductDetailsContract.ProductDetailsView {
    override var getLayout = R.layout.activity_product_details
    override fun init() {

    }

    override fun showProductDetailsSuccess(response: ProductDetailsResponse) {

    }

    override fun showProductDetailsError(err_message: String) {

    }
}
