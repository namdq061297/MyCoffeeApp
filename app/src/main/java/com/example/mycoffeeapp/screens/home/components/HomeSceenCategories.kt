package com.example.mycoffeeapp.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun HomeScreenCategories() {
  var selectedCategory by remember { mutableIntStateOf(0) }
  val categories = listOf(
    "All Coffee",
    "Tea",
    "Latte",
    "Espresso",
    "Cappuccino",
    "Mocha",
    "Frappuccino",
    "Sandwiches",
    "Smoothies",
  )
  // Danh sách category cuộn ngang
  LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
    itemsIndexed(categories) { index, category ->
      CategoryChip(
        label = category,
        selected = index == selectedCategory,
        onClick = { selectedCategory = index },
      )
    }
  }
}