package com.example.mycoffeeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mycoffeeapp.data.local.LocalStorageService
import com.example.mycoffeeapp.navigation.AppNavGraph
import com.example.mycoffeeapp.ui.theme.MyCoffeeAppTheme

class MainActivity : ComponentActivity() {
    private lateinit var localStorage: LocalStorageService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        localStorage = LocalStorageService(applicationContext)
        enableEdgeToEdge()
        setContent {
            MyCoffeeAppTheme {
                AppNavGraph(localStorage = localStorage)
            }
        }
    }
}