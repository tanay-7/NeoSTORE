package com.example.neostore.ui.homescreen

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.neostore.R
import kotlinx.android.synthetic.main.item_drawer.view.*

class DrawerAdapter(
    val items: ArrayList<DrawerModel>,
    val context: Context,
    var onMenuItemListener: OnMenuItemListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var menuItemListener: OnMenuItemListener

    init {
        this.menuItemListener = onMenuItemListener
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val root = LayoutInflater.from(context).inflate(R.layout.item_drawer, p0, false)
        return DrawerViewHolder(root, menuItemListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        (p0 as DrawerViewHolder).tvDrawerItem.text = items[p1].menuItem     //Type-Casting of p0 to DrawerViewHolder.
        p0.ivMenuIcon.setImageResource(items[p1].menuIcon)
    }
}

class DrawerViewHolder(view: View, onMenuItemListener: OnMenuItemListener) : RecyclerView.ViewHolder(view),
    View.OnClickListener {

    var onMenuItemListener: OnMenuItemListener

    init {
        this.onMenuItemListener = onMenuItemListener
        view.setOnClickListener(this)
    }

    // Holds the TextView that will add each menu item to
    val tvDrawerItem = view.tv_draweritem
    val ivMenuIcon = view.iv_menuicon

    override fun onClick(v: View?) {
        onMenuItemListener.onMenuItemClick(adapterPosition)
    }
}

interface OnMenuItemListener {
    fun onMenuItemClick(position: Int)
}