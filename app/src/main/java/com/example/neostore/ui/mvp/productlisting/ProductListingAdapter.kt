package com.example.neostore.ui.mvp.productlisting

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.neostore.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product_listing.view.*

class ProductListingAdapter(
    var items: ArrayList<ProductListData>?,
    val context: Context,
    var onProductClickListener: OnProductClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var productClickListener: OnProductClickListener

    init {
        this.productClickListener = onProductClickListener
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val root = LayoutInflater.from(context).inflate(R.layout.item_product_listing, p0, false)
        return ProductListingViewHolder(root, productClickListener)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {

        //Bind Title
        (p0 as ProductListingViewHolder).tvProductTitle.text =
            items!![p1].name    //Type-Casting of p0 to ProductListingViewHolder.

        //Bind Subtitle
        p0.tvProductSubtitle.text = items!![p1].producer

        //Bind Price
        p0.tvProductPrice.text = items!![p1].cost.toString()

        //Bind Image
        Picasso.with(context).load(items!![p1].product_images).into(p0.ivProductImage)

        //Bind Rating Star
        p0.rbProductRating.rating = items!![p1].rating.toFloat()
    }
}

class ProductListingViewHolder(view: View, onProductClickListener: OnProductClickListener) :
    RecyclerView.ViewHolder(view),
    View.OnClickListener {

    var onProductClickListener: OnProductClickListener

    init {
        this.onProductClickListener = onProductClickListener
        view.setOnClickListener(this)
    }

    // Holds the Title,Image,etc.. that will add each menu item to
    val tvProductTitle = view.txt_product_title
    val tvProductSubtitle = view.txt_product_subtitle
    val ivProductImage = view.iv_product
    val tvProductPrice = view.txt_product_price
    val rbProductRating = view.rb_product_rating

    override fun onClick(v: View?) {
        onProductClickListener.onProductClick(adapterPosition)
    }
}

interface OnProductClickListener {
    fun onProductClick(position: Int)
}
