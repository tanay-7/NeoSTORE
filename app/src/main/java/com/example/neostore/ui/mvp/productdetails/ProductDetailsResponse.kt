package com.example.neostore.ui.mvp.productdetails

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class ProductDetailsData {
    @SerializedName("id")
    @Expose
    val id: Int? = null
    @SerializedName("product_category_id")
    @Expose
    val productCategoryId: Int? = null
    @SerializedName("name")
    @Expose
    val name: String? = null
    @SerializedName("producer")
    @Expose
    val producer: String? = null
    @SerializedName("description")
    @Expose
    val description: String? = null
    @SerializedName("cost")
    @Expose
    val cost: Int? = null
    @SerializedName("rating")
    @Expose
    val rating: Int? = null
    @SerializedName("view_count")
    @Expose
    val viewCount: Int? = null
    @SerializedName("created")
    @Expose
    val created: String? = null
    @SerializedName("modified")
    @Expose
    val modified: String? = null
    @SerializedName("product_images")
    @Expose
    val productImages: List<ProductDetailsImages>? = null
}

class ProductDetailsResponse {
    @SerializedName("status")
    @Expose
    val status: Int? = null
    @SerializedName("data")
    @Expose
    val data: ProductDetailsData? = null
}

class ProductDetailsImages {

    @SerializedName("id")
    @Expose
    val id: Int? = null
    @SerializedName("product_id")
    @Expose
    val productId: Int? = null
    @SerializedName("image")
    @Expose
    val image: String? = null
    @SerializedName("created")
    @Expose
    val created: String? = null
    @SerializedName("modified")
    @Expose
    val modified: String? = null

}
