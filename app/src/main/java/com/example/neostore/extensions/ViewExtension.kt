package com.example.neostore.extensions

import android.view.View
import com.example.neostore.R

fun View.onClick(block: (View) -> Unit) {
    this.setOnClickListener { view -> block(view) }
}

fun View.hideView() {
    this.visibility = View.INVISIBLE
}

fun View.goneView() {
    this.visibility = View.GONE
}

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun StringBuilder.bindRs(): StringBuilder {
    return this.insert(0, "Rs.")
}

fun StringBuilder.bindComma(): StringBuilder {
    return when (this.length) {
        4 -> this.insert(1, ",")
        5 -> this.insert(2, ",")
        else -> this.insert(0, "")
    }
}

fun StringBuilder.bindRupeeSign(): StringBuilder {
    return this.insert(0, R.string.rupee_sign)
}
