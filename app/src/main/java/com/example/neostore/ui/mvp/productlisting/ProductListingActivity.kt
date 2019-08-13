package com.example.neostore.ui.mvp.productlisting

import android.content.Intent
import com.google.android.material.navigation.NavigationView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.example.neostore.R
import com.example.neostore.extensions.goneView
import com.example.neostore.extensions.showView
import com.example.neostore.ui.base.BaseActivity
import com.example.neostore.ui.mvp.productdetails.ProductDetailsActivity
import kotlinx.android.synthetic.main.activity_product_listing.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import kotlinx.android.synthetic.main.spinner_layout.*

class ProductListingActivity : BaseActivity(), ProductListingContract.ProductListingView,
    NavigationView.OnNavigationItemSelectedListener, OnProductClickListener {

    override var getLayout = R.layout.activity_product_listing
    lateinit var productListingPresenter: ProductListingPresenter   //Presenter
    lateinit var productList: ArrayList<ProductListData>
    private lateinit var productAdapter: ProductListingAdapter

    override fun init() {
        productListingPresenter = ProductListingPresenter(this)
        val productId = intent.extras?.get("product_id").toString()
        setMyActionBar()
        setToolbarTitle(productId)
        onToolbarBackClick()
        initLoader()
        spin_kit.showView()
        productListingPresenter.getProductList(productId, "10", "1")//API Call
    }

    private fun recyclerViewOnScrollListener() {
        rv_product_listing.addOnScrollListener(object : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: androidx.recyclerview.widget.RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = (rv_product_listing.layoutManager as androidx.recyclerview.widget.LinearLayoutManager).itemCount
                val lastItemOnScreen =
                    (rv_product_listing.layoutManager as androidx.recyclerview.widget.LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                Toast.makeText(
                    this@ProductListingActivity,
                    "${lastItemOnScreen + 1} OF $totalItemCount",
                    Toast.LENGTH_SHORT
                ).show()

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
            "2" -> txt_product_toolbar.text = "Chairs"
            "3" -> txt_product_toolbar.text = "Sofas"
            "4" -> txt_product_toolbar.text = "Cupboards"
        }
    }

    private fun setRecyclerView(productListDataList: ArrayList<ProductListData>) {
        productList = productListDataList
        val rvProductListing = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rv_product_listing) //Recycler
        rvProductListing.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(this)// Creates a vertical Layout Manager
        productAdapter = ProductListingAdapter(productListDataList, this@ProductListingActivity, this)
        rvProductListing.adapter = productAdapter
        recyclerViewOnScrollListener()
    }

    /*private fun setActionBar() {
        toolbarProductListings = customToolbar.toolbar_product_listings
        setSupportActionBar(toolbar_product_listings)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }*/

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_icon_menu, menu)
        val searchItem = menu!!.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        //To remove search icon from keypad and It will transform magnifying button of keypad to check button.
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                productAdapter.filter.filter(newText)
                return false
            }
        })
        return true
    }

    override fun showProductSuccess(response: ProductListResponse) {
        setRecyclerView(response.data)
        spin_kit.goneView()
    }

    override fun showProductError(err_message: String) {

    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProductClick(position: Int) {
        val currProductId = productList[position].id
        val intent = Intent(this, ProductDetailsActivity::class.java)
        intent.putExtra("product_id", currProductId)
        startActivity(intent)
    }
}
