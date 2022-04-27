package com.appscals.mybudolchecklistapp.presentation.appComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.appscals.mybudolchecklistapp.R

@Composable
fun MainScreen(navController: NavController) {

    Scaffold(
        topBar = { AppToolBar("MY BUDOL LIST") }
    ) {
        PageCoverPhoto()
    }
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