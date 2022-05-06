package com.appscals.mybudolchecklistapp.data.repository

import com.appscals.mybudolchecklistapp.data.local.dao.ItemsDao
import com.appscals.mybudolchecklistapp.domain.model.Item
import com.appscals.mybudolchecklistapp.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataRepositoryImpl
@Inject constructor(private val dao: ItemsDao) : DataRepository {
    override fun getItems(): Flow<List<Item>> {
        return dao.getItems()
    }

    override suspend fun insertItem(item: Item) {
        dao.insertItem(item)
    }

    override suspend fun getItemByID(id: Int): Item? {
        return dao.getItemById(id)
    }
}