package com.example.neostore.ui.mvp.productlisting

import com.example.neostore.network.Api
import com.example.neostore.network.RetrofitClient
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProductListingPresenter(productListingView: ProductListingContract.ProductListingView) :
    ProductListingContract.ProductListingPresenter {

    lateinit var apiService: Api
    lateinit var results: ProductListResponse
    var productListingView: ProductListingContract.ProductListingView

    init {
        this.productListingView = productListingView
    }

    override fun getProductList(product_category_id: String, limit: String, page: String) {
        val productListingObserver: Observer<ProductListResponse> = getProductListingObserver()
        apiService = RetrofitClient.getInstance().getClient().create(Api::class.java)
        apiService.getProductList(product_category_id, limit, page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(productListingObserver)
    }

    private fun getProductListingObserver(): Observer<ProductListResponse> {
        return object : Observer<ProductListResponse> {
            override fun onSubscribe(d: Disposable) {
                //showLoader
            }

            override fun onNext(t: ProductListResponse) {
                results = t
            }

            override fun onComplete() {
                //hideLoader
                productListingView.showProductSuccess(results)
            }

            override fun onError(e: Throwable) {
                //hideLoader
            }
        }
    }
}