package com.example.neostore.ui.mvvm.address

import android.arch.persistence.room.Entity

@Entity(tableName = "address_table")
data class AddressEntity(
    var address: String,
    var landmark: String,
    var city: String,
    var state: String,
    var zipCode: String,
    var country: String
)