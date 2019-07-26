package com.example.neostore.ui.mvp.productlisting

import android.content.Intent
import android.support.design.widget.NavigationView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.Toast
import com.example.neostore.R
import com.example.neostore.extensions.goneView
import com.example.neostore.extensions.onClick
import com.example.neostore.extensions.showView
import com.example.neostore.ui.base.BaseActivity
import com.example.neostore.ui.mvp.productdetails.ProductDetailsActivity
import kotlinx.android.synthetic.main.activity_product_listing.*
import kotlinx.android.synthetic.main.custom_toolbar.view.*
import kotlinx.android.synthetic.main.spinner_layout.*

class ProductListingActivity : BaseActivity(), ProductListingContract.ProductListingView,
    NavigationView.OnNavigationItemSelectedListener, OnProductClickListener {

    lateinit var toolbarProductListings: Toolbar
    override var getLayout = R.layout.activity_product_listing
    lateinit var productListingPresenter: ProductListingPresenter   //Presenter
    lateinit var productList: ArrayList<ProductListData>

    override fun init() {
        onBackClick()
        setMyActionBar()
        productListingPresenter = ProductListingPresenter(this)
        val productId = intent.extras?.get("product_id").toString()
        setToolbarTitle(productId)
        initLoader()
        spin_kit.showView()
        productListingPresenter.getProductList(productId, "10", "1")//API Call
    }

    private fun recyclerViewOnScrollListener() {
        rv_product_listing.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = (rv_product_listing.layoutManager as LinearLayoutManager).itemCount
                val lastItemOnScreen =
                    (rv_product_listing.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                Toast.makeText(
                    this@ProductListingActivity,
                    "${lastItemOnScreen + 1} OF $totalItemCount",
                    Toast.LENGTH_SHORT
                )
                    .show()

                //Custom Toast: Getting the View object as defined in the customtoast.xml file
                /*val customToastLayout: View =
                    layoutInflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_layout))

                customToastLayout.findViewById<TextView>(R.id.tv_count).text = lastItemOnScreen.toString()
                customToastLayout.findViewById<TextView>(R.id.tv_out_of_count).text = totalItemCount.toString()

                var toast = Toast(this@ProductListingActivity)
                toast.duration = Toast.LENGTH_SHORT
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.view = customToastLayout
                toast.show()*/
            }
        })
    }

    private fun setToolbarTitle(productId: String) {
        when (productId) {
            "2" -> customToolbar.txt_product_toolbar.text = "Chairs"
            "3" -> customToolbar.txt_product_toolbar.text = "Sofas"
            "4" -> customToolbar.txt_product_toolbar.text = "Cupboards"
        }
    }

    private fun setRecyclerView(productListDataList: ArrayList<ProductListData>) {
        productList = productListDataList
        val rvProductListing = findViewById<RecyclerView>(R.id.rv_product_listing) //Recycler
        rvProductListing.layoutManager = LinearLayoutManager(this)// Creates a vertical Layout Manager
        val productAdapter = ProductListingAdapter(productListDataList, this@ProductListingActivity, this)
        rvProductListing.adapter = productAdapter
        recyclerViewOnScrollListener()
    }

    private fun setMyActionBar() {
        toolbarProductListings = customToolbar.toolbar_product_listings
        setSupportActionBar(toolbarProductListings)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_icon_menu, menu)
        return true
    }*/

    override fun showProductSuccess(response: ProductListResponse) {
        setRecyclerView(response.data)
        spin_kit.goneView()
    }

    private fun onBackClick() {
        customToolbar.img_back_button.onClick {
            finish()
        }
    }

    override fun showProductError(err_message: String) {

    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProductClick(position: Int) {
        var currProductId = productList[position].id
        val intent = Intent(this, ProductDetailsActivity::class.java)
        intent.putExtra("product_id", currProductId)
        startActivity(intent)
    }
}
