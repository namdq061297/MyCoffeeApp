package com.example.mycoffeeapp.screens.wishlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycoffeeapp.mock.MockData
import com.example.mycoffeeapp.model.Product
import com.example.mycoffeeapp.navigation.Routes
import com.example.mycoffeeapp.screens.ui_components.BottomAppNavBar
import com.example.mycoffeeapp.ui.theme.CharcoalGray

private val WishlistBackground = Color(0xFFF9F2ED)
private val RowBackground = Color(0xFFE8E8E8)
private val SecondaryText = Color(0xFF808080)
private val DeleteRed = Color(0xFFD32F2F)

@Composable
fun WishlistScreen(
    products: List<Product>,
    onProductClick: (Product) -> Unit = {},
    onRemove: (Product) -> Unit = {},
    onBottomNavClick: (String) -> Unit = {},
) {
    Scaffold(
        containerColor = WishlistBackground,
        bottomBar = {
            BottomAppNavBar(currentRoute = Routes.WISHLIST, onItemClick = onBottomNavClick)
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {
                Text(
                    text = "Wishlist",
                    color = CharcoalGray,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 8.dp),
                )
            }

            if (products.isEmpty()) {
                item {
                    Text(
                        text = "Your wishlist is empty",
                        color = SecondaryText,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(horizontal = 16.dp),
                    )
                }
            } else {
                items(products, key = { it.id }) { product ->
                    WishlistRow(
                        product = product,
                        onClick = { onProductClick(product) },
                        onRemove = { onRemove(product) },
                    )
                }
            }
        }
    }
}

@Composable
private fun WishlistRow(
    product: Product,
    onClick: () -> Unit,
    onRemove: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(RowBackground)
            .clickable(onClick = onClick)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = product.imageUrl),
            contentDescription = product.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(8.dp)),
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = product.name,
                color = CharcoalGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = product.description,
                color = SecondaryText,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        IconButton(onClick = onRemove) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Remove ${product.name} from wishlist",
                tint = DeleteRed,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WishlistScreenPreview() {
    WishlistScreen(products = MockData.productMockData.take(3))
}
