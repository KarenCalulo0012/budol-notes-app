package com.appscals.mybudolchecklistapp.presentation.itemVM

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appscals.mybudolchecklistapp.domain.model.Item
import com.appscals.mybudolchecklistapp.domain.use_cases.GetItemUseCase
import com.appscals.mybudolchecklistapp.domain.use_cases.InsertItemUseCase
import com.appscals.mybudolchecklistapp.presentation.addItem.EditEvent
import com.appscals.mybudolchecklistapp.presentation.addItem.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditItemViewModel
@Inject constructor(
    private val insertItemUseCase: InsertItemUseCase,
    private val getItemUseCase: GetItemUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _itemName = mutableStateOf(TextFieldState())
    val itemName: State<TextFieldState> = _itemName

    private val _itemPrice = mutableStateOf(TextFieldState())
    val itemPrice: State<TextFieldState> = _itemPrice

    private val _itemLink = mutableStateOf(TextFieldState())
    val itemLink: State<TextFieldState> = _itemLink

    private val _itemStatus = mutableStateOf(TextFieldState())
    val itemStatus: State<TextFieldState> = _itemStatus

    private val _itemStore = mutableStateOf(TextFieldState())
    val itemStore: State<TextFieldState> = _itemStore

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentItemId: Int? = null

    init {
        savedStateHandle.get<Int>("itemId")?.let { id ->
            if (id != -1) {
                insertItem(id)
            }
        }
    }

    private fun insertItem(id: Int) {
        viewModelScope.launch {
            getItemUseCase(id)?.also { item ->
                currentItemId = item.id
                _itemName.value = itemName.value.copy(
                    text = item.itemName
                )
                _itemPrice.value = itemPrice.value.copy(
                    text = item.price
                )
                _itemLink.value = itemLink.value.copy(
                    text = item.link.orEmpty()
                )
                _itemStatus.value = itemStatus.value.copy(
                    text = item.status
                )
                _itemStore.value = itemStore.value.copy(
                    text = item.shopName
                )
            }
        }
    }

    fun onEvent(event: EditEvent) {
        when (event) {
            is EditEvent.InsertItem -> {
                viewModelScope.launch {
                    insertItemUseCase(
                        Item(
                            id = currentItemId,
                            itemName = itemName.value.text,
                            price = itemPrice.value.text,
                            link = itemLink.value.text,
                            status = itemStatus.value.text,
                            shopName = itemStore.value.text
                        )
                    )
                    _eventFlow.emit(UiEvent.SaveItem)
                }
            }
            is EditEvent.EnteredLink -> {
                _itemLink.value = itemLink.value.copy(
                    text = event.value
                )
            }
            is EditEvent.EnteredName -> {
                _itemName.value = itemName.value.copy(
                    text = event.value
                )
            }
            is EditEvent.EnteredPrice -> {
                _itemPrice.value = itemPrice.value.copy(
                    text = event.value
                )
            }
            is EditEvent.EnteredStatus -> {
                _itemStatus.value = itemStatus.value.copy(
                    text = event.value
                )
            }
            is EditEvent.EnteredStore -> {
                _itemStore.value = itemStore.value.copy(
                    text = event.value
                )
            }
        }
    }
}


sealed class UiEvent {
    object SaveItem : UiEvent()
}
