package com.appscals.mybudolchecklistapp.presentation.displayItem

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ThumbDown
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ReviewBar(liked: Boolean) {

    val tintColor: Color = if (liked) {
        MaterialTheme.colors.secondary
    } else {
        Color.Gray
    }

    Row(
        modifier = Modifier
            .requiredHeight(24.dp)
            .padding(top = 8.dp)
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Rounded.ThumbUp,
                contentDescription = "",
                tint = tintColor
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Rounded.ThumbDown,
                contentDescription = "",
                tint = tintColor
            )
        }
    }
}