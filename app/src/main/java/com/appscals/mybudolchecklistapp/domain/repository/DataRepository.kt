package com.appscals.mybudolchecklistapp.domain.repository

import com.appscals.mybudolchecklistapp.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface DataRepository {

    fun getItems(): Flow<List<Item>>

    suspend fun insertItem(item: Item)

    suspend fun getItemByID(id: Int): Item?
}