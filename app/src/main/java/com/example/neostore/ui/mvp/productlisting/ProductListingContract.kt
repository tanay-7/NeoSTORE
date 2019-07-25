package com.example.neostore.ui.mvp.productlisting

interface ProductListingContract {
    interface ProductListingView {
        fun showProductSuccess(response: ProductListResponse)
        fun showProductError(err_message: String)
    }

    interface ProductListingPresenter {
        fun getProductList(
            product_category_id: String,
            limit: String,
            page: String
        )
    }
}