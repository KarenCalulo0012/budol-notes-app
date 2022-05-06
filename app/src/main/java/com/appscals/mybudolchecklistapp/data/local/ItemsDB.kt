package com.appscals.mybudolchecklistapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.appscals.mybudolchecklistapp.data.local.dao.ItemsDao
import com.appscals.mybudolchecklistapp.domain.model.Item

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemsDB : RoomDatabase() {
    abstract val itemsDao: ItemsDao
}