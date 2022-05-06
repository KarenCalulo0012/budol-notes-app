package com.appscals.mybudolchecklistapp.domain.use_cases

import com.appscals.mybudolchecklistapp.domain.model.Item
import com.appscals.mybudolchecklistapp.domain.repository.DataRepository
import javax.inject.Inject

class InsertItemUseCase @Inject constructor(private val repository: DataRepository) {
    suspend operator fun invoke(item: Item) {
        repository.insertItem(item)
    }
}