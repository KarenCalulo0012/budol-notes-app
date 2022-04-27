package com.appscals.mybudolchecklistapp.presentation.appComponents

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.appscals.mybudolchecklistapp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreenAnimate(navController: NavController) {
    val scale = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.5f,
            animationSpec = tween(
                durationMillis = 500,
                easing = { OvershootInterpolator(2f).getInterpolation(it) }
            )
        )
        delay(1000L)
        navController.navigate("main_screen")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEF5A3D))
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "Splash Image",
            modifier = Modifier
                .width(400.dp)
                .height(400.dp)
                .scale(scale.value)
        )
    }
}