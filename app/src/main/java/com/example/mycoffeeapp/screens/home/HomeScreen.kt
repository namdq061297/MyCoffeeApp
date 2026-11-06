package com.example.mycoffeeapp.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycoffeeapp.R
import com.example.mycoffeeapp.model.Product
import com.example.mycoffeeapp.screens.home.components.HomeScreenCategories
import com.example.mycoffeeapp.screens.home.components.ProductGrid
import com.example.mycoffeeapp.screens.ui_components.BottomAppNavBar
import com.example.mycoffeeapp.screens.ui_components.SearchBar

@Preview(showBackground = true)
@Composable
fun HomeScreen(
    onProductClick: (Product) -> Unit = {},
    onBottomNavClick: (String) -> Unit = {},
) {
    val location = "Hanoi, Vietnam" // Replace with your actual location

    Scaffold(
        bottomBar = { BottomAppNavBar(onItemClick = onBottomNavClick) }
    ) { innerPadding ->
        // Toàn bộ màn hình là một LazyVerticalGrid duy nhất → cuộn đồng nhất.
        // Các section phía trên được đưa vào topContent (item đầu tiên, chiếm full 2 cột).
        ProductGrid(
            modifier = Modifier.padding(innerPadding),
            onProductClick = onProductClick,
            topContent = { HomeHeader(location = location) },
        )
    }
}

@Composable
private fun HomeHeader(location: String) {
    Box(modifier = Modifier.fillMaxWidth()) {
        // Nền gradient tối phía sau phần trên. Nằm trong item của grid nên
        // không dùng được fillMaxHeight (chiều cao không giới hạn) → đặt chiều cao cố định,
        // đủ để kéo xuống khoảng giữa banner.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
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

        Column(modifier = Modifier.padding(16.dp)) {
            Text("Location", color = Color.Gray, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
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
            HomeScreenCategories()
        }
    }
}