package com.example.neostore.extensions

import android.view.View

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
