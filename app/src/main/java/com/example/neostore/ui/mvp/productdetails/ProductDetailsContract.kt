package com.example.neostore.ui.mvp.productdetails

interface ProductDetailsContract {
    interface ProductDetailsView {
        fun showProductDetailsSuccess(response: ProductDetailsResponse)
        fun showProductDetailsError(err_message: String)
    }

    interface ProductDetailsPresenter {
        fun getProductDetails(
            product_id: Number
        )
    }
}