package com.example.neostore.ui.mvvm.address

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.neostore.R

class AddressListAdapter : androidx.recyclerview.widget.RecyclerView.Adapter<AddressListAdapter.AddressListViewHolder>() {
    private lateinit var mItems: List<AddressEntity>

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AddressListViewHolder {
        val root = LayoutInflater.from(p0.context).inflate(R.layout.item_address_list, p0, false)
        return AddressListViewHolder(root)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(p0: AddressListViewHolder, p1: Int) {
        p0.onBind()
    }

    fun setItems(mItems: List<AddressEntity>?) {
        this.mItems = mItems!!
        notifyDataSetChanged()
    }

    fun getItem(position: Int): AddressEntity {
        return mItems[position]
    }

    inner class AddressListViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        fun onBind() {
            val customerName = itemView.findViewById<TextView>(R.id.tv_customer_name)
            val customerAddress = itemView.findViewById<TextView>(R.id.tv_customer_address)

            customerName.text = "Demo Customer"
            customerAddress.text =
                (
                        mItems[adapterPosition].address +
                                mItems[adapterPosition].landmark +
                                mItems[adapterPosition].city +
                                mItems[adapterPosition].state +
                                mItems[adapterPosition].country +
                                mItems[adapterPosition].zipCode
                        )
        }
    }
}