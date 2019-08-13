package com.example.neostore.ui.mvvm.address

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AddressDao {
    @Insert
    fun insert(item: AddressEntity)

    @Delete
    fun delete(item: AddressEntity)

    @Query("DELETE FROM address_table")
    fun deleteAllAddresses()

    @Query("SELECT * FROM address_table")
    fun getAllAddresses(): LiveData<List<AddressEntity>>
}