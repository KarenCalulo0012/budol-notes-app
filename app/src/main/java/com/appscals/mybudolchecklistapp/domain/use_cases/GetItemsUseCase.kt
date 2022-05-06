package com.appscals.mybudolchecklistapp.domain.use_cases

import com.appscals.mybudolchecklistapp.domain.model.Item
import com.appscals.mybudolchecklistapp.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(private val repository: DataRepository) {
    operator fun invoke(): Flow<List<Item>> {
        return repository.getItems()
    }
}