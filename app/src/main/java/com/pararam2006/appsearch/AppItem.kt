package com.pararam2006.appsearch

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun AppItem(
    iconUri: Uri,
    label: String,
    onClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier.clickable {
            onClick()
        }
    ) {
        AsyncImage(
            model = iconUri,
            contentDescription = "App icon",
            modifier = Modifier.size(70.dp)
        )
        Text(text = label, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}