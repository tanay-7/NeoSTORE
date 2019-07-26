package com.example.neostore.ui.mvp.productdetails

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.neostore.R
import com.example.neostore.ui.base.BaseActivity

class ProductDetailsActivity : BaseActivity(), ProductDetailsContract.ProductDetailsView, OnImageClickListener {
    override var getLayout = R.layout.activity_product_details
    private var productId: Number = 0
    lateinit var productDetailsList: List<ProductDetailsImages>
    lateinit var productDetailsPresenter: ProductDetailsPresenter //Presenter

    override fun init() {
        productId = Integer.valueOf(intent.extras.get("product_id").toString())
        productDetailsPresenter = ProductDetailsPresenter(this)
        productDetailsPresenter.getProductDetails(productId)
    }

    private fun setRecyclerView(productDetailsImageList: List<ProductDetailsImages>) {
        productDetailsList = productDetailsImageList
        val rvProductDetails = findViewById<RecyclerView>(R.id.rv_product_images) //Recycler
        rvProductDetails.layoutManager = LinearLayoutManager(this)// Creates a vertical Layout Manager
        val productDetailsAdapter = ProductDetailsAdapter(productDetailsImageList, this@ProductDetailsActivity,this)
        rvProductDetails.adapter = productDetailsAdapter
    }

    override fun showProductDetailsSuccess(response: ProductDetailsResponse) {
        setRecyclerView(response.data!!.productImages!!)
    }

    override fun showProductDetailsError(err_message: String) {

    }

    override fun onImageClick(position: Int) {

    }
}
