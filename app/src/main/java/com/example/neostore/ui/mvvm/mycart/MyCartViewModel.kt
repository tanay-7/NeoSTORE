package com.example.neostore.ui.mvvm.mycart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

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