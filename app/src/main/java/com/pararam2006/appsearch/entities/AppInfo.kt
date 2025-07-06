package com.pararam2006.appsearch.entities

import android.net.Uri

data class AppInfo(
    val label: String,
    val iconUri: Uri,
    val packageName: String,
    val name: String
)