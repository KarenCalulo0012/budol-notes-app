package com.appscals.mybudolchecklistapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appscals.mybudolchecklistapp.domain.model.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemsDao {

    @Query("SELECT * FROM Item")
    fun getItems(): Flow<List<Item>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(items: Item)

    @Query("SELECT * FROM Item WHERE id = :id")
    suspend fun getItemById(id: Int): Item?
}