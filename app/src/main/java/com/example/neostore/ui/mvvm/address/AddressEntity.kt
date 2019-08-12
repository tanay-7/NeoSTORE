package com.example.neostore.ui.mvvm.address

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "address_table")
data class AddressEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var address: String,
    var landmark: String,
    var city: String,
    var state: String,
    var zipCode: String,
    var country: String
)