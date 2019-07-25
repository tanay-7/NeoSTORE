package com.example.neostore.ui.mvp.productdetails

import android.content.Context
import com.example.neostore.network.Api
import com.example.neostore.network.RetrofitClient
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProductDetailsPresenter(productDetailsView: ProductDetailsContract.ProductDetailsView, context: Context) :
    ProductDetailsContract.ProductDetailsPresenter {

    lateinit var apiService: Api
    lateinit var results: ProductDetailsResponse
    var productDetailsView: ProductDetailsContract.ProductDetailsView

    init {
        this.productDetailsView = productDetailsView
    }

    override fun getProductDetails(product_id: String) {
        val productDetailsObserver: Observer<ProductDetailsResponse> = getProductDetailsObserver()
        apiService = RetrofitClient.provideRetro().create(Api::class.java)
        apiService.getProductDetails(product_id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(productDetailsObserver)
    }

    private fun getProductDetailsObserver(): Observer<ProductDetailsResponse> {
        return object : Observer<ProductDetailsResponse> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: ProductDetailsResponse) {
                results = t
            }

            override fun onComplete() {
                productDetailsView.showProductDetailsSuccess(results)
            }

            override fun onError(e: Throwable) {

            }
        }
    }
}