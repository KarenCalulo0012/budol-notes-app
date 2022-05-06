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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appscals.mybudolchecklistapp.R
import com.appscals.mybudolchecklistapp.presentation.appComponents.AppToolBar
import com.appscals.mybudolchecklistapp.presentation.itemVM.AddEditItemViewModel
import com.appscals.mybudolchecklistapp.presentation.itemVM.UiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddItemScreen(
    navController: NavController,
    viewModel: AddEditItemViewModel = hiltViewModel()
) {

    val itemNameState = viewModel.itemName.value.text
    val itemPriceState = viewModel.itemPrice.value.text
    val itemLinkState = viewModel.itemLink.value.text
    val itemStatusState = viewModel.itemStatus.value.text
    val itemStoreState = viewModel.itemStore.value.text

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                UiEvent.SaveItem -> navController.navigateUp()
            }
        }
    }

    Scaffold(
        topBar = { AppToolBar(title = "ADD ITEM") },
        bottomBar = {
            EditBottomBar(
                onInsertUser = { viewModel.onEvent(EditEvent.InsertItem) }
            )
        }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AddEditCoverPhoto()
            AddEditContent(
                name = itemNameState,
                price = itemPriceState,
                link = itemLinkState,
                status = itemStatusState,
                store = itemStoreState,
                onEvent = { viewModel.onEvent(it) }
            )
        }
    }
}

@Composable
fun EditBottomBar(onInsertUser: () -> Unit) {
    Button(
        onClick = { onInsertUser() },
        shape = RoundedCornerShape(30.dp),
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

@Composable
fun AddEditContent(
    name: String,
    price: String,
    link: String,
    status: String,
    store: String,
    onEvent: (EditEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        CustomTextField(
            name,
            "Item Name",
            Icons.Rounded.Sell
        ) { onEvent(EditEvent.EnteredName(it)) }
        CustomTextField(
            price,
            "Item Price",
            Icons.Rounded.Paid
        ) { onEvent(EditEvent.EnteredPrice(it)) }
        CustomTextField(
            link,
            "Item Link",
            Icons.Rounded.Link
        ) { onEvent(EditEvent.EnteredLink(it)) }
        CustomTextField(
            status,
            "Status",
            Icons.Rounded.Quiz
        ) { onEvent(EditEvent.EnteredStatus(it)) }
        CustomTextField(
            store,
            "Store",
            Icons.Rounded.StoreMallDirectory
        ) { onEvent(EditEvent.EnteredStore(it)) }
        Spacer(modifier = Modifier.padding(top = 14.dp))
    }
}

@Composable
fun AddEditCoverPhoto() {
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
    state: String,
    label: String,
    icon: ImageVector?,
    modifier: Modifier = Modifier,
    onTextChange: (String) -> Unit
) {
    OutlinedTextField(
        value = state,
        onValueChange = onTextChange,
        label = { Text(text = label) },
        singleLine = true,
        shape = RoundedCornerShape(30.dp),
        leadingIcon = { icon?.let { Icon(imageVector = it, contentDescription = label) } },
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, top = 4.dp)
            .fillMaxWidth()
    )
}