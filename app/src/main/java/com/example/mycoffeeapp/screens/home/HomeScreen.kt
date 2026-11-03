package com.example.mycoffeeapp.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycoffeeapp.screens.ui_components.BottomAppNavBar
import com.example.mycoffeeapp.screens.ui_components.SearchBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    val location = "Hanoi, Vietnam" // Replace with your actual location
    Scaffold(
        bottomBar = { BottomAppNavBar() }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f / 3f)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF303030),
                            Color(0xFF1F1F1f),
                            Color(0xFF121212)
                        )
                    )
                )
        ) {
            // Add content for the top third of the screen here
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(innerPadding)
            ) {
                // Add content for the bottom two-thirds of the screen here
                Text("Location", color = Color.Gray, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Row (verticalAlignment = Alignment.CenterVertically) {
                    Text(location, color = Color.White, fontSize = 16.sp)
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Change Location",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                SearchBar(
                    query = "",
                    onQueryChange = {},
                    onFilterClick = {}
                )

            }
        }


    }
}