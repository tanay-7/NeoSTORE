package com.example.neostore.ui.mvvm.address

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

class AddressRepository(application: Application) {
    private var addressDao: AddressDao
    private var allAddresses: LiveData<ArrayList<AddressEntity>>

    init {
        val addressDatabase: AddressDatabase = AddressDatabase.getInstance(application)
        addressDao = addressDatabase.addressDao()
        allAddresses = addressDao.getAllAddresses()
    }

    fun insert(item: AddressEntity) {
        InsertAsyncTask(addressDao).execute(item)
    }

    fun delete(item: AddressEntity) {
        DeleteAsyncTask(addressDao).execute(item)
    }

    fun deleteAllAddresses() {
        DeleteAllAddressesAsyncTask(addressDao).execute()
    }

    fun getAllAddress(): LiveData<ArrayList<AddressEntity>> {
        return allAddresses
    }

    //Room doesn't allows DB Operation on main thread.hence, we created AsyncTask to do DB Operations on background thread.
    companion object {
        private class InsertAsyncTask(addressDao: AddressDao) : AsyncTask<AddressEntity, Unit, Unit>() {

            private val addressDao: AddressDao = addressDao
            override fun doInBackground(vararg params: AddressEntity?) {
                params[0]?.let { addressDao.insert(it) }
            }
        }

        private class DeleteAsyncTask(addressDao: AddressDao) : AsyncTask<AddressEntity, Unit, Unit>() {

            private val addressDao: AddressDao = addressDao
            override fun doInBackground(vararg params: AddressEntity?) {
                params[0]?.let { addressDao.delete(it) }
            }
        }

        private class DeleteAllAddressesAsyncTask(addressDao: AddressDao) : AsyncTask<Unit, Unit, Unit>() {

            private val addressDao: AddressDao = addressDao
            override fun doInBackground(vararg params: Unit?) {
                addressDao.deleteAllAddresses()
            }
        }
    }
}