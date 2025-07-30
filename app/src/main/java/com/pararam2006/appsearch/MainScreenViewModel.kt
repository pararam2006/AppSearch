package com.pararam2006.appsearch

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import com.pararam2006.appsearch.entities.AppInfo

class MainScreenViewModel : ViewModel() {
    private val pm = MyApp.instance.packageManager
    private var appInfoListOriginal by mutableStateOf<List<AppInfo>>(emptyList())
    var appInfoList by mutableStateOf<List<AppInfo>>(emptyList())
        private set
    var input by mutableStateOf("")

    fun getApps() {
        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }
        appInfoListOriginal = pm.queryIntentActivities(intent, 0)
            .filter { it.loadLabel(pm) != "AppSearch" }
            .map {
                AppInfo(
                    label = it.loadLabel(pm).toString(),
                    iconUri = "android.resource://${it.activityInfo.packageName}/${it.activityInfo.applicationInfo.icon}".toUri(),
                    packageName = it.activityInfo.packageName,
                    name = it.activityInfo.name,
                )
            }
            .sortedBy { it.label }
        onInputChange("")
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
        input = newInput
        appInfoList = appInfoListOriginal.filter {
            it.label.startsWith(newInput, true)
        }
    }
}