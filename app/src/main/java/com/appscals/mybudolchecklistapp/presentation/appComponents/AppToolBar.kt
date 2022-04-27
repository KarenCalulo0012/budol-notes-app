package com.appscals.mybudolchecklistapp.presentation.appComponents

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun AppToolBar(title: String) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Rounded.ArrowBack, "backIcon")
            }
        },
        backgroundColor = MaterialTheme.colors.secondary,
        contentColor = MaterialTheme.colors.background,
        elevation = 0.dp
    )
}