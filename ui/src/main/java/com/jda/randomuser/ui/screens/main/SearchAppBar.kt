package com.jda.randomuser.ui.screens.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(onCloseClick: () -> Unit, onClearClick: () -> Unit, searchText: String, onTextChange: (String) -> Unit) {
    OutlinedTextField(modifier = Modifier.fillMaxWidth(),
        value = searchText,
        onValueChange = {
            onTextChange(it)
        },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "search icon")
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    if (searchText.isNotEmpty()) {
                        onClearClick()
                    } else {
                        onCloseClick()
                    }
                }
            ) {
                Icon(imageVector = Icons.Filled.Close, contentDescription = "close")
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.White,
            focusedBorderColor = Color.LightGray
        ),
        placeholder = {
            Text(text = "Search", color = Color.LightGray)
        }
    )
}
