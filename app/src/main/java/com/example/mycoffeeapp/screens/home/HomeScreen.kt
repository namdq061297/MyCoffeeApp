package com.example.mycoffeeapp.screens.home

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycoffeeapp.screens.ui_components.BottomAppNavBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    Scaffold (
        bottomBar = { BottomAppNavBar() }
    ){

    }
}