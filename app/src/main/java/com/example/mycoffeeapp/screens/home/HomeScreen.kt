package com.example.mycoffeeapp.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycoffeeapp.R
import com.example.mycoffeeapp.screens.ui_components.BottomAppNavBar
import com.example.mycoffeeapp.screens.ui_components.CategoryChip
import com.example.mycoffeeapp.screens.ui_components.HomeScreenCategories
import com.example.mycoffeeapp.screens.ui_components.SearchBar

@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    val location = "Hanoi, Vietnam" // Replace with your actual location

    Scaffold(
        bottomBar = { BottomAppNavBar() }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Lớp nền gradient tối ở 1/3 trên màn hình
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
            )

            // Nội dung nằm đè lên nền, không bị giới hạn trong 1/3 màn hình
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
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
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.banner_1),
                    contentDescription = "Banner 1",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
                Spacer(modifier = Modifier.height(24.dp))

                HomeScreenCategories()

            }
        }


    }
}