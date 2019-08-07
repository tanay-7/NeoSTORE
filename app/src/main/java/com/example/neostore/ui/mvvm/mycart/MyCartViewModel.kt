package com.example.neostore.ui.mvvm.mycart

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

class MyCartViewModel(context: Application) : AndroidViewModel(context) {
    private var mCartItems: MutableLiveData<ArrayList<MyCartResponse>>? = null
    private lateinit var mRepo: MyCartRepository

    fun init() {
        if (mCartItems != null) {
            return
        }
        mRepo = MyCartRepository.getInstance(getApplication())!!
        mCartItems = mRepo.getCartItems()
    }

    fun getCartItems(): LiveData<ArrayList<MyCartResponse>>? {
        return mCartItems
    }

}