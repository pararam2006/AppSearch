package com.pararam2006.appsearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.pararam2006.appsearch.ui.theme.AppSearchTheme

class MainActivity : ComponentActivity() {
    private val mainScreenViewModel: MainScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            LaunchedEffect(Unit) {
                mainScreenViewModel.getApps()
            }

            AppSearchTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding ->
                        MainScreen(
                            appInfoList = mainScreenViewModel.appInfoList,
                            modifier = Modifier.padding(innerPadding),
                            onClick = { appInfo ->
                                mainScreenViewModel.startApp(appInfo, this)
                            },
                            onInputChange = mainScreenViewModel::onInputChange,
                            input = mainScreenViewModel.input
                        )
                    }
                )
            }
        }
    }
}