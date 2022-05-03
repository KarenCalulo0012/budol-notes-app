package com.appscals.mybudolchecklistapp.presentation.addItem

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.appscals.mybudolchecklistapp.R
import com.appscals.mybudolchecklistapp.presentation.appComponents.AppToolBar

@Composable
fun AddItemScreen() {
    val itemNameState = remember { mutableStateOf("") }
    val itemPriceState = remember { mutableStateOf("") }
    val itemLinkState = remember { mutableStateOf("") }
    val itemStatusState = remember { mutableStateOf("") }
    Scaffold(topBar = { AppToolBar(title = "ADD ITEM") }) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                backgroundColor = MaterialTheme.colors.secondary,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_shopping_img),
                    contentDescription = null,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                CustomTextField(itemNameState, "Item Name", Icons.Rounded.Sell)
                CustomTextField(itemPriceState, "Item Price", Icons.Rounded.Paid)
                CustomTextField(itemLinkState, "Item Link", Icons.Rounded.Link)
                CustomTextField(itemStatusState, "Status", Icons.Rounded.Quiz)
                CustomDropdownMenu()
                Spacer(modifier = Modifier.padding(top = 14.dp))
                Button(
                    onClick = { /*TODO*/ }, shape = RoundedCornerShape(30.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "SAVE",
                        color = Color.White,
                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CustomDropdownMenu() {
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Shopee", "Lazada")
    var selectedText by remember { mutableStateOf("") }

    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    Box(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 4.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            shape = RoundedCornerShape(30.dp),
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textFieldSize = coordinates.size.toSize()
                },
            label = { Text("Store") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.StoreMallDirectory,
                    contentDescription = ""
                )
            },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label
                }) {
                    Text(text = label)
                }
            }
        }
    }

}

@Composable
fun CustomTextField(
    state: MutableState<String>,
    label: String,
    icon: ImageVector?
) {
    OutlinedTextField(
        value = state.value,
        onValueChange = { state.value = it },
        label = { Text(text = label) },
        singleLine = true,
        shape = RoundedCornerShape(30.dp),
        leadingIcon = { icon?.let { Icon(imageVector = it, contentDescription = label) } },
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 4.dp)
            .fillMaxWidth()
    )
}