package com.appscals.mybudolchecklistapp.presentation.itemVM

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appscals.mybudolchecklistapp.domain.model.Item
import com.appscals.mybudolchecklistapp.domain.use_cases.GetItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ItemViewModel
@Inject constructor(
    private val getItemsUseCase: GetItemsUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(ItemsState())
    val state: State<ItemsState> = _state

    init {
        getItemList()
    }

    private fun getItemList() {
        getItemsUseCase().onEach { _items ->
            _state.value = state.value.copy(items = _items)
        }.launchIn(viewModelScope)
    }
}

data class ItemsState(
    val items: List<Item> = emptyList()
)