package com.example.neostore.ui.productlisting

data class ProductListResponse(
    var status: Number,
    var message: String,
    var user_msg: String,
    var data: Array<Data>
)

data class Data(
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
    var product_images: String
)
