package com.appscals.mybudolchecklistapp.presentation.addItem

sealed class EditEvent {
    data class EnteredName(val value: String) : EditEvent()
    data class EnteredPrice(val value: String) : EditEvent()
    data class EnteredLink(val value: String) : EditEvent()
    data class EnteredStatus(val value: String) : EditEvent()
    data class EnteredStore(val value: String) : EditEvent()
    object InsertItem : EditEvent()
}
