package com.example.neostore.ui.mvp.productdetails

data class ProductDetailsResponse(
    var status: Number,
    var message: String,
    var user_msg: String,
    var data: ArrayList<ProductDetailsData>
)

data class ProductDetailsData(
    var id: Number,
    var product_category_id: Number,
    var name: String,
    var producer: String,
    var description: String,
    var cost: Number,
    var rating: Number,
    var view_count: Number,
    var created: Number,
    var modified: Number,
    var product_images: ArrayList<ProductDetailsImages>
)

data class ProductDetailsImages(
    var id: Number,
    var product_id: Number,
    var image: String,
    var created: Number,
    var modified: Number
)
