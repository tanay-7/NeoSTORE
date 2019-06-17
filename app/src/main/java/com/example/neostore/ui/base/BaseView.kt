package com.example.neostore.ui.base

interface BaseView {
    fun initPresenter()
    fun showLoader()
    fun closeLoader()
    fun showError()
}