package com.example.mycoffeeapp.screens.ui_components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycoffeeapp.R
import com.example.mycoffeeapp.navigation.Routes
import com.example.mycoffeeapp.ui.theme.LightBrown

@Preview(showBackground = true)
@Composable
fun BottomAppNavBar(
  currentRoute: String = Routes.HOME,
  onItemClick: (String) -> Unit = {},
) {
  val navBarItems = listOf(
    BottomAppNavBarItem(
      icon = R.drawable.regular_outline_home,
      label = "Home",
      route = Routes.HOME
    ),
    BottomAppNavBarItem(
      icon = R.drawable.regular_outline_bag,
      label = "Cart",
      route = Routes.CART
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

  NavigationBar(
    containerColor = MaterialTheme.colorScheme.surface,
    modifier = Modifier.height(100.dp)
  ) {
    navBarItems.forEach { item ->
      NavigationBarItem(
        selected = item.route == currentRoute,
        onClick = { onItemClick(item.route) },
        icon = {
          Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.label
          )
        },
        label = { Text(text = item.label) },
        colors = NavigationBarItemDefaults.colors(
          selectedTextColor = LightBrown,
          selectedIconColor = LightBrown,
          unselectedIconColor = Color.DarkGray,
          unselectedTextColor = Color.DarkGray,
          indicatorColor = LightBrown.copy(alpha = 0.1f)
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