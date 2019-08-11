package com.example.neostore.ui.mvvm.address

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.support.annotation.NonNull

class AddressViewModel(@NonNull application: Application) : AndroidViewModel(application) {
    private var mRepo: AddressRepository = AddressRepository(application)
    private var allAddresses: LiveData<ArrayList<AddressEntity>>

    init {
        allAddresses = mRepo.getAllAddress()
    }

    fun insert(item: AddressEntity) {
        mRepo.insert(item)
    }

    fun delete(item: AddressEntity) {
        mRepo.delete(item)
    }

    fun deleteAllAddresses() {
        mRepo.deleteAllAddresses()
    }

    fun getAllAddresses(): LiveData<ArrayList<AddressEntity>> {
        return allAddresses
    }
}