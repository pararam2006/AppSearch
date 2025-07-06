package com.pararam2006.appsearch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import com.pararam2006.appsearch.entities.AppInfo

@Composable
fun MainScreen(
    appInfoList: List<AppInfo>,
    onClick: (AppInfo) -> Unit,
    onInputChange: (String) -> Unit,
    input: String,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }
    val gridState = rememberLazyGridState()

    Column(
        modifier = modifier.fillMaxSize().imePadding(),
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier.weight(1f),
            state = gridState
        ) {
            items(appInfoList, key = {it.iconUri}) { item ->
                AppItem(
                    iconUri = item.iconUri,
                    label = item.label,
                    onClick = {
                        onClick(item)
                    }
                )
            }
        }

        OutlinedTextField(
            value = input,
            onValueChange = onInputChange,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            singleLine = true,
            trailingIcon = { IconButton(onClick = {
                onInputChange("")
            }) { Icon(imageVector = Icons.Filled.Clear, "") } }
        )
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    // Скроллим в начало при изменении текста
    LaunchedEffect(input) {
        if (appInfoList.isNotEmpty()) {
            gridState.scrollToItem(0)
        }
    }
}