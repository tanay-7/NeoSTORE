package com.example.neostore.ui.mvvm.mycart

data class MyCartResponse(
    var product_id: Number,
    var product_name: String,
    var product_price: Number,
    var product_category: String,
    var product_producer: String,
    var product_description: String,
    var product_rating: Number,
    var product_image: String
)

/*
data class MyCartResponse(
    var status: Boolean,
    var message: String,
    var user_msg: String,
    var data: ArrayList<Data>
)

data class Data(
    var id: Number,
    var product_id: Number,
    var quantity: String,
    var count: Number,
    var total: Number,
    var product: Product
)

data class Product(
    var id: Number,
    var name: String,
    var product_category: Number,
    var cost: Number,
    var product_images: String,
    var sub_total: Number
)*/
