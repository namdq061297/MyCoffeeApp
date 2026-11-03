package com.example.mycoffeeapp.screens.home.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mycoffeeapp.R
import com.example.mycoffeeapp.mock.MockData
import com.example.mycoffeeapp.model.Product

// Ảnh tạm trong drawable, lặp lại theo vị trí sản phẩm
private val productImages = listOf(
    R.drawable.coffee_1,
    R.drawable.coffee_2,
    R.drawable.coffee_3,
    R.drawable.coffee_4,
    R.drawable.coffee_5,
    R.drawable.coffee_6,
)

@Composable
fun ProductGrid(
    modifier: Modifier = Modifier,
    products: List<Product> = MockData.productMockData,
    onProductClick: (Product) -> Unit = {},
    onAddToCart: (Product) -> Unit = {},
) {
    // Lưới 2 cột; khoảng cách giữa các thẻ đến từ padding(8.dp) bên trong ProductCard
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
    ) {
        itemsIndexed(products, key = { _, product -> product.id }) { index, product ->
            ProductCard(
                product = product,
                imageRes = productImages[index % productImages.size],
                onClick = { onProductClick(product) },
                onAddClick = { onAddToCart(product) },
            )
        }
    }
}
