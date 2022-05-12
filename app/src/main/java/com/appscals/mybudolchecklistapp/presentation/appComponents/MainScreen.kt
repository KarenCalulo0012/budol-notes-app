package com.appscals.mybudolchecklistapp.presentation.appComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appscals.mybudolchecklistapp.R
import com.appscals.mybudolchecklistapp.presentation.displayItem.DisplayItemScreen
import com.appscals.mybudolchecklistapp.presentation.itemVM.ItemViewModel

@Composable
fun MainScreen(navController: NavController, viewModel: ItemViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = { AppToolBar("MY ITEM LIST") },
        floatingActionButton = { ItemFAB() { navController.navigate(AppScreen.AddItemScreen.route) } }
    ) {
        PageCoverPhoto()
        DisplayItemScreen(
            scrollState,
            onEditItem = { id -> navController.navigate(AppScreen.AddItemScreen.passId(id)) },
            itemList = state.items
        )
    }
}

@Composable
fun ItemFAB(onFabClicked: () -> Unit) {
    FloatingActionButton(
        onClick = onFabClicked,
        backgroundColor = MaterialTheme.colors.primary,
        content = {
            Icon(imageVector = Icons.Rounded.Add, contentDescription = "", tint = Color.White)
        })
}

@Composable
fun PageCoverPhoto() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.cover_pic),
            contentDescription = "cover",
            modifier = Modifier.requiredHeight(150.dp),
            contentScale = ContentScale.Crop,
        )
    }
}