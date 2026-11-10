package com.example.mycoffeeapp.screens.ui_components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mycoffeeapp.R
import com.example.mycoffeeapp.ui.theme.LightBrown

// Tim tô đậm khi Product đã có trong Wishlist, tim viền khi chưa có
@Composable
fun WishlistHeart(
    isInWishlist: Boolean,
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    outlineTint: Color = LightBrown,
) {
    if (isInWishlist) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Remove from wishlist",
            tint = LightBrown,
            modifier = modifier.size(size),
        )
    } else {
        Icon(
            painter = painterResource(id = R.drawable.regular_outline_heart),
            contentDescription = "Add to wishlist",
            tint = outlineTint,
            modifier = modifier.size(size),
        )
    }
}
