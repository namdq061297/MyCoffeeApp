package com.example.mycoffeeapp.screens.home.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mycoffeeapp.mock.MockData
import com.example.mycoffeeapp.model.Product


@Composable
fun ProductGrid(
  modifier: Modifier = Modifier,
  products: List<Product> = MockData.productMockData,
  onProductClick: (Product) -> Unit = {},
  onAddToCart: (Product) -> Unit = {},
  topContent: (@Composable () -> Unit)? = null,
) {
  // Lưới 2 cột; khoảng cách giữa các thẻ đến từ padding(8.dp) bên trong ProductCard
  LazyVerticalGrid(
    columns = GridCells.Fixed(2),
    modifier = modifier.fillMaxSize(),
  ) {
    // Nếu có topContent, hiển thị nó ở đầu danh sách
    topContent?.let {
      item(span = { GridItemSpan(maxLineSpan) }) {
        it()
      }
    }
    itemsIndexed(products, key = { _, product -> product.id }) { index, product ->
      ProductCard(
        product = product,
        onClick = { onProductClick(product) },
        onAddClick = { onAddToCart(product) },
      )
    }
  }
}
