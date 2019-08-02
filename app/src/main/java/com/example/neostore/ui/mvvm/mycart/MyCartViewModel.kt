package com.example.neostore.ui.mvvm.mycart

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel


class MyCartViewModel : ViewModel() {
    private lateinit var mCartItems: MutableLiveData<MyCartResponse>

    fun addNewValue() {

    }

    fun getCartItems(): LiveData<MyCartResponse> {
        return mCartItems
    }

}