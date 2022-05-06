package com.appscals.mybudolchecklistapp.domain.use_cases

import com.appscals.mybudolchecklistapp.domain.model.Item
import com.appscals.mybudolchecklistapp.domain.repository.DataRepository
import javax.inject.Inject

class GetItemUseCase
@Inject constructor(private val repository: DataRepository) {
    suspend operator fun invoke(id: Int): Item? {
        return repository.getItemByID(id)
    }
}