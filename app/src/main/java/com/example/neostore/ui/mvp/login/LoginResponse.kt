package com.example.neostore.ui.mvp.login

data class LoginResponse(
    var id: Int = 0,
    var message: String = "",
    var data: DataProvider
)

data class DataProvider(
    var first_name: String = "",
    var last_name: String = ""
)