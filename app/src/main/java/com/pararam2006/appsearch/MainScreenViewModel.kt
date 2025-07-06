package com.pararam2006.appsearch

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pararam2006.appsearch.entities.AppInfo

class MainScreenViewModel: ViewModel() {
    private val pm = MyApp.instance.packageManager
    var appInfoList by mutableStateOf<List<AppInfo>>(emptyList())
        private set
    val input = mutableStateOf("")

    fun getApps() {
        Log.d("MainScreenViewModel", "Begin")
        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }
        appInfoList = pm.queryIntentActivities(intent, 0)
            .map {
                AppInfo(
                    label = it.loadLabel(pm).toString(),
                    iconUri = Uri.parse("android.resource://${it.activityInfo.packageName}/${it.activityInfo.applicationInfo.icon}"),
                    packageName = it.activityInfo.packageName,
                    name = it.activityInfo.name,
                )
            }
            .sortedBy { it.label }
        Log.d("MainScreenViewModel", "End")
    }

    fun startApp(appInfo: AppInfo, context: Context) {
        val intent = Intent().apply {
            setClassName(
                appInfo.packageName,
                appInfo.name
            )
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }

        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun onInputChange(newInput: String) {
        input.value = newInput
    }
}