package com.example.neostore.ui.mvvm.address

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface AddressDao {
    @Insert
    fun insert(item: AddressEntity)

    @Delete
    fun delete(item: AddressEntity)

    @Query("DELETE FROM address_table")
    fun deleteAllAddresses()

    @Query("SELECT ADDRESS,LANDMARK,CITY,STATE,ZIPCODE,COUNTRY FROM address_table")
    fun getAllAddresses(): LiveData<ArrayList<AddressEntity>>
}