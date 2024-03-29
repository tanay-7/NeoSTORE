package com.example.neostore.ui.mvp.productdetails

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.neostore.R
import com.example.neostore.database.DBHandlerProductDetails
import com.example.neostore.extensions.bindComma
import com.example.neostore.extensions.bindRs
import com.example.neostore.extensions.onClick
import com.example.neostore.ui.base.BaseActivity
import com.example.neostore.ui.mvvm.mycart.MyCartActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class ProductDetailsActivity : BaseActivity(), ProductDetailsContract.ProductDetailsView, OnImageClickListener {
    override var getLayout = R.layout.activity_product_details
    private var productId: Number = 0
    lateinit var productDetailsList: List<ProductDetailsImages>
    lateinit var productDetailsPresenter: ProductDetailsPresenter //Presenter
    var helper = DBHandlerProductDetails(this)
    private lateinit var alertDialog: AlertDialog.Builder
    private lateinit var currSelectedImage: String
    private lateinit var productResponse: ProductDetailsData

    override fun init() {
        setMyActionBar()
        onToolbarBackClick()
        productId = Integer.valueOf(intent.extras.get("product_id").toString())
        productDetailsPresenter = ProductDetailsPresenter(this)
        //initLoader()
        //spin_kit.showView()
        productDetailsPresenter.getProductDetails(productId)
        onAddToCartBtnClick()
    }

    private fun onAddToCartBtnClick() {
        btn_add_to_cart.onClick {
            var obj = ProductDetailsData()
            obj.id = productId as Int
            obj.name = tv_product_title.text.toString()
            obj.cost = 0//Integer.valueOf(tv_product_price.text.toString())
            obj.productCategoryId = productResponse.productCategoryId
            obj.producer = tv_shop_name.text.toString()
            obj.description = tv_description_body.text.toString()
            obj.rating = rb_product_rating.progress
            obj.created = currSelectedImage
            var insertSuccess = helper.insertData(obj)
            if (insertSuccess == "Success") {
                makeToast("Item Added Successfully")
                val intent = Intent(this, MyCartActivity::class.java)
                startActivity(intent)
                //initDialog()
            } else {
                makeToast("Item Already Exists")
            }
            onChangeToBuyNow()
        }
    }

    private fun onChangeToBuyNow() {
        YoYo.with(Techniques.FlipInX)
            .duration(1000)
            .repeat(1)
            .playOn(btn_add_to_cart)
        btn_add_to_cart.text = "BUY NOW"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            btn_add_to_cart.backgroundTintList = ContextCompat.getColorStateList(this, R.color.color_red)
        }
    }

    private fun initDialog() {
        alertDialog = AlertDialog.Builder(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            alertDialog.setView(R.layout.custom_popup)
        }
        alertDialog.show()
    }

    private fun setRecyclerView(productDetailsImageList: List<ProductDetailsImages>) {
        productDetailsList = productDetailsImageList
        val rvProductDetails = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rv_product_images) //Recycler
        rvProductDetails.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(
                this,
                androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL,
                false
            )// Creates a horizontal Layout Manager
        val productDetailsAdapter = ProductDetailsAdapter(productDetailsImageList, this@ProductDetailsActivity, this)
        rvProductDetails.adapter = productDetailsAdapter
    }

    override fun showProductDetailsSuccess(response: ProductDetailsResponse) {
        setRecyclerView(response.data!!.productImages!!)
        setResponseToProduct(response.data)
        productResponse = response.data
        toolbarSetting(response.data.name)
        //spin_kit.goneView()
    }

    private fun toolbarSetting(name: String?) {
        txt_product_toolbar.text = name
        onToolbarBackClick()
    }

    private fun setResponseToProduct(detailsData: ProductDetailsData) {
        currSelectedImage = productDetailsList[0].image!!
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
        currSelectedImage = productDetailsList[position].image!!
    }
}
