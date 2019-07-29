package com.example.neostore.ui.mvp.productdetails

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.neostore.R
import com.squareup.picasso.Picasso

class ProductDetailsAdapter(
    var items: List<ProductDetailsImages>?,
    val context: Context,
    val listner: OnImageClickListener
) : RecyclerView.Adapter<ProductDetailsAdapter.ProductDetailsViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProductDetailsViewHolder {
        val root = LayoutInflater.from(context).inflate(R.layout.item_product_images, p0, false)
        return ProductDetailsViewHolder(root)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    override fun onBindViewHolder(p0: ProductDetailsViewHolder, p1: Int) {
        p0.bind()
    }

    inner class ProductDetailsViewHolder(var itemViews: View) : RecyclerView.ViewHolder(itemViews) {
        fun bind() {
            val imageListner = itemViews.findViewById<ImageView>(R.id.iv_item_product_img)
            var isSelected = false
            Picasso.with(context).load(items!![adapterPosition].image).into(imageListner)

            imageListner.setOnClickListener {
                listner.onImageClick(adapterPosition)
                if (!isSelected) {
                    it.background =
                        ContextCompat.getDrawable(context, R.drawable.rounded_button_box_red)
                    isSelected = true
                } else {
                    it.background = null
                }
            }
        }
    }
}

interface OnImageClickListener {
    fun onImageClick(position: Int)
}
