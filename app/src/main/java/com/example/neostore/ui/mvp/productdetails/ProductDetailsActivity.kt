package com.example.neostore.ui.mvp.productdetails

import android.app.AlertDialog
import android.os.Build
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.neostore.R
import com.example.neostore.database.DBHandlerProductDetails
import com.example.neostore.extensions.bindComma
import com.example.neostore.extensions.bindRs
import com.example.neostore.extensions.onClick
import com.example.neostore.ui.base.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_details.*

class ProductDetailsActivity : BaseActivity(), ProductDetailsContract.ProductDetailsView, OnImageClickListener {
    override var getLayout = R.layout.activity_product_details
    private var productId: Number = 0
    lateinit var productDetailsList: List<ProductDetailsImages>
    lateinit var productDetailsPresenter: ProductDetailsPresenter //Presenter
    var helper = DBHandlerProductDetails(this)
    private lateinit var alertDialog: AlertDialog.Builder

    override fun init() {
        setMyActionBar()
        onToolbarBackClick()
        productId = Integer.valueOf(intent.extras.get("product_id").toString())
        productDetailsPresenter = ProductDetailsPresenter(this)
        //initLoader()
        //spin_kit.showView()
        productDetailsPresenter.getProductDetails(productId)
        onBuyNowBtnClick()
    }

    private fun onBuyNowBtnClick() {
        btn_buy_now.onClick {
            var obj = ProductDetailsData()
            obj.id = productId as Int
            obj.name = tv_product_title.text.toString()
            obj.cost = 0//Integer.valueOf(tv_product_price.text.toString())
            obj.producer = tv_shop_name.text.toString()
            obj.description = tv_description_body.text.toString()
            obj.rating = rb_product_rating.progress
            var insertSuccess = helper.insertData(obj)
            if (insertSuccess == "Success") {
                makeToast("Item Added Successfully")
                initDialog()
            } else {
                makeToast("Item Already Exists")
            }

        }
        Log.e("SqlData: ", helper.retrieveData().toString())
    }

    private fun initDialog() {
        alertDialog = AlertDialog.Builder(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            alertDialog.setView(R.layout.custom_popup)
        }
        alertDialog.show()

        /*tv_close.setOnClickListener(object:DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog?.dismiss()
            }
        })*/
    }

    private fun setRecyclerView(productDetailsImageList: List<ProductDetailsImages>) {
        productDetailsList = productDetailsImageList
        val rvProductDetails = findViewById<RecyclerView>(R.id.rv_product_images) //Recycler
        rvProductDetails.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)// Creates a horizontal Layout Manager
        val productDetailsAdapter = ProductDetailsAdapter(productDetailsImageList, this@ProductDetailsActivity, this)
        rvProductDetails.adapter = productDetailsAdapter
    }

    override fun showProductDetailsSuccess(response: ProductDetailsResponse) {
        setRecyclerView(response.data!!.productImages!!)
        setResponseToProduct(response.data)
        //spin_kit.goneView()
    }

    private fun setResponseToProduct(detailsData: ProductDetailsData) {
        Picasso.with(this).load(productDetailsList[0].image).into(iv_product)
        tv_product_title.text = detailsData.name
        tv_product_category.text = when (detailsData.productCategoryId) {
            1 -> "Table"
            2 -> "Chair"
            3 -> "Sofa"
            4 -> "Cupboard"
            else -> "Category"
        }
        tv_shop_name.text = detailsData.producer
        rb_product_rating.progress = detailsData.rating!!
        tv_product_price.text = StringBuilder(detailsData.cost.toString()).bindComma().bindRs()
        tv_description_body.text = detailsData.description
    }

    override fun showProductDetailsError(err_message: String) {
        //spin_kit.goneView()
    }

    override fun onImageClick(position: Int) {
        Picasso.with(this).load(productDetailsList[position].image).into(iv_product)
    }
}
