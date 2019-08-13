package com.example.neostore.ui.mvvm.mycart

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.neostore.R
import com.example.neostore.extensions.bindRupeeSign
import com.squareup.picasso.Picasso

class MyCartAdapter(
    var items: ArrayList<MyCartResponse>?,
    val context: Context
) : androidx.recyclerview.widget.RecyclerView.Adapter<MyCartAdapter.MyCartViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyCartViewHolder {
        var root = LayoutInflater.from(context).inflate(R.layout.item_cart, p0, false)
        return MyCartViewHolder(root)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    override fun onBindViewHolder(p0: MyCartViewHolder, p1: Int) {
        p0.onBind()
    }

    inner class MyCartViewHolder(var itemViews: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemViews) {
        fun onBind() {
            val productImage = itemViews.findViewById<ImageView>(R.id.iv_cart_product)
            val productName = itemViews.findViewById<TextView>(R.id.tv_item_name)
            val productCategory = itemViews.findViewById<TextView>(R.id.tv_item_category)
            val productPrice = itemViews.findViewById<TextView>(R.id.tv_product_price)

            Picasso.with(context).load(items!![adapterPosition].product_image).into(productImage)
            productName.text = items!![adapterPosition].product_name
            productCategory.text = items!![adapterPosition].product_category
            productPrice.text = StringBuilder(items!![adapterPosition].product_price.toString()).bindRupeeSign()
        }
    }
}