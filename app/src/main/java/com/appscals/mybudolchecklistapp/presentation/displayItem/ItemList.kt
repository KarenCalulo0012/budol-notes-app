package com.appscals.mybudolchecklistapp.presentation.displayItem

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Link
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appscals.mybudolchecklistapp.R
import com.appscals.mybudolchecklistapp.model.Items
import java.util.*

@Composable
fun ItemList(item: Items) {
    val uriHandler = LocalUriHandler.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp
    ) {
        Column() {
            Text(
                text = item.status.uppercase(Locale.getDefault()),
                fontSize = 14.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(top = 8.dp, end = 16.dp, bottom = 8.dp)
                    .fillMaxWidth(),
                fontWeight = FontWeight.SemiBold,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = shopCondition(item.shopName)),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .align(Alignment.CenterVertically)
                        .clip(CircleShape)
                        .border(2.dp, MaterialTheme.colors.secondary, CircleShape)
                )
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(25.dp)
                    ) {
                        Text(text = item.itemName, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        IconButton(
                            onClick = {
                                if (item.link.isNullOrEmpty()) {
                                    //
                                } else {
                                    uriHandler.openUri(item.link)
                                }
                            },
                            modifier = Modifier.align(Alignment.Top)
                        ) {
                            Icon(imageVector = Icons.Rounded.Link, contentDescription = "")
                        }
                    }
                    Text(text = "₱${item.price}", fontSize = 14.sp, fontWeight = FontWeight.Light)
                    ReviewBar(liked = item.rating)
                }
            }
        }

    }
}

fun shopCondition(shop: String): Int {
    if (shop == "Lazada") {
        return R.drawable.lazada
    }
    return R.drawable.shopee
}
