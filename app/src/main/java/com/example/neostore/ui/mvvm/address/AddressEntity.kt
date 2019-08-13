package com.example.neostore.ui.mvvm.address

import androidx.room.Entity
import androidx.room.PrimaryKey

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