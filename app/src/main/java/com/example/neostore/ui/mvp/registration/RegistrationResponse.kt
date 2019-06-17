package com.example.neostore.ui.mvp.registration

data class RegistrationResponse(
    var status: Int,
    var message: String,
    var data: Data
)

data class Data(
    var id: Int,
    var first_name: String,
    var last_name: String,
    var email: String,
    var username: String,
    var profile_pic: String,
    var country_id: Int,
    var gender: String,
    var phone_no: String,
    var dob: String,
    var is_active: Boolean
)