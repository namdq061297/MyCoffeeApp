package com.example.mycoffeeapp.screens.ui_components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycoffeeapp.R

@Preview(showBackground = true)
@Composable
fun BottomAppNavBar() {
    val navBarItems = listOf(
        BottomAppNavBarItem(icon = R.drawable.regular_outline_home, label = "Home", route = "home"),
        BottomAppNavBarItem(
            icon = R.drawable.regular_outline_bag,
            label = "Cart",
            route = "cart"
        ),
        BottomAppNavBarItem(
            icon = R.drawable.regular_outline_heart,
            label = "Favorites",
            route = "favorites"
        ),
        BottomAppNavBarItem(
            icon = R.drawable.outline_account_circle_24,
            label = "Profile",
            route = "profile"
        )
    )
    var selectedIndex by remember { mutableIntStateOf(0) }

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier.height(100.dp)
    ) {
        navBarItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { selectedIndex = index },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.label
                    )
                },
                label = { Text(text = item.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = MaterialTheme.colorScheme.surface
                )
            )
            // Add navigation bar item logic here
        }
    }
}

data class BottomAppNavBarItem(
    val icon: Int,
    val label: String,
    val route: String
)