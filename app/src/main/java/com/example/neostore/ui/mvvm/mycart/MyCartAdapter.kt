package com.example.neostore.ui.mvvm.mycart

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.neostore.R
import com.example.neostore.ui.mvp.productdetails.ProductDetailsImages

class MyCartAdapter(
    var items: List<ProductDetailsImages>?,
    val context: Context
) : RecyclerView.Adapter<MyCartAdapter.MyCartViewHolder>() {
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

    inner class MyCartViewHolder(var itemViews: View) : RecyclerView.ViewHolder(itemViews) {
        fun onBind() {

        }
    }
}