package com.appscals.mybudolchecklistapp.model

data class Items(
    val id: Int = 0,
    val itemName: String,
    val price: String,
    val status: String,
    val rating: Boolean,
    val shopName: String,
    val link: String?,
)
