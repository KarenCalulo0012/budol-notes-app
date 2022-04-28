package com.appscals.mybudolchecklistapp.presentation.displayItem

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appscals.mybudolchecklistapp.model.data.itemList
import com.appscals.mybudolchecklistapp.presentation.displayItem.ItemList

@Composable
fun DisplayItemScreen(scrollState: ScrollState) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 150.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .scrollable(scrollState, Orientation.Vertical)
        ) {
            items(itemList) { data ->
                ItemList(item = data)
            }
        }
    }
}