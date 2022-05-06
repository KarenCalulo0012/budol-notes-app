package com.appscals.mybudolchecklistapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "item_name")
    val itemName: String,
    val price: String,
    val status: String,
    @ColumnInfo(name = "shop_name")
    val shopName: String,
    val link: String?,
)
