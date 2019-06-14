package com.example.neostore.features.login

data class LoginResponse(
    var id: Int = 0,
    var message: String = "",
    var data: DataProvider
)

data class DataProvider(
    var first_name: String = "",
    var last_name: String = ""
)