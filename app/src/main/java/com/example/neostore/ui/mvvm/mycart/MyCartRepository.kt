package com.example.neostore.ui.mvvm.mycart

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.example.neostore.database.DBHandlerProductDetails

class MyCartRepository {
    companion object {
        private var mInstance: MyCartRepository? = null
        private var dataSet = ArrayList<MyCartResponse>()
        private lateinit var application: Application

        fun getInstance(application: Application): MyCartRepository? {
            this.application = application
            if (mInstance == null) {
                mInstance = MyCartRepository()
            }
            return mInstance
        }
    }

    fun getCartItems(): MutableLiveData<ArrayList<MyCartResponse>> {
        setCartItems()
        var data = MutableLiveData<ArrayList<MyCartResponse>>()
        data.value = dataSet
        return data
    }

    private fun setCartItems() {
        dataSet = DBHandlerProductDetails(application)
            .retrieveData()
    }
}