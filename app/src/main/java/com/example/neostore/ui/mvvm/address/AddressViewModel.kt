package com.example.neostore.ui.mvvm.address

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.annotation.NonNull

class AddressViewModel(@NonNull application: Application) : AndroidViewModel(application) {
    private var mRepo: AddressRepository = AddressRepository(getApplication())
    private var allAddresses: LiveData<List<AddressEntity>>

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

    fun getAllAddresses(): LiveData<List<AddressEntity>> {
        return allAddresses
    }
}