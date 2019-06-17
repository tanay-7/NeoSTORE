package com.example.neostore.ui.mvp.registration

interface RegistrationContract {
    interface RegistartionView {
        fun showRegistrationSuccess(message: String)
        fun showRegistrationError(err_message: String)
    }

    interface RegistrationPresenter {
        fun register(
            first_name: String,
            last_name: String,
            email: String,
            password: String,
            confirm_password: String,
            gender: String,
            phone_no: String
        )
    }
}