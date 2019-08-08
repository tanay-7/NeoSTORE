package com.example.neostore.ui.mvp.productlisting

import android.content.Context
import android.support.v7.widget.AppCompatRatingBar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import com.example.neostore.R
import com.example.neostore.extensions.bindComma
import com.example.neostore.extensions.bindRs
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product_listing.view.*

class ProductListingAdapter(
    var items: ArrayList<ProductListData>?,
    val context: Context,
    var onProductClickListener: OnProductClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var productClickListener: OnProductClickListener = onProductClickListener
    var itemsFull = ArrayList(items)

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
        p0.tvProductPrice.text = StringBuilder(items!![p1].cost.toString()).bindComma().bindRs()

        //Bind Image
        Picasso.with(context).load(items!![p1].product_images).into(p0.ivProductImage)

        //Bind Rating Star
        p0.rbProductRating.rating = items!![p1].rating.toFloat()
    }

    override fun getFilter(): Filter {
        return itemsFilter
    }

    private var itemsFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: ArrayList<ProductListData> = ArrayList()

            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(itemsFull)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim()

                for (item in itemsFull) {
                    if (item.name.toLowerCase().trim().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }

            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            items!!.clear()
            items!!.addAll(results!!.values as ArrayList<ProductListData>)
            notifyDataSetChanged()
        }
    }
}

class ProductListingViewHolder(view: View, onProductClickListener: OnProductClickListener) :
    RecyclerView.ViewHolder(view),
    View.OnClickListener {

    var onProductClickListener: OnProductClickListener = onProductClickListener

    init {
        view.setOnClickListener(this)
    }

    // Holds the Title,Image,etc.. that will add each menu item to
    val tvProductTitle: TextView = view.txt_product_title
    val tvProductSubtitle: TextView = view.txt_product_subtitle
    val ivProductImage: ImageView = view.iv_product
    val tvProductPrice: TextView = view.tv_product_price
    val rbProductRating: AppCompatRatingBar = view.rb_product_rating

    override fun onClick(v: View?) {
        onProductClickListener.onProductClick(adapterPosition)
    }
}

interface OnProductClickListener {
    fun onProductClick(position: Int)
}
