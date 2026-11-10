package com.example.mycoffeeapp.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import com.example.mycoffeeapp.model.Product
import com.example.mycoffeeapp.screens.ui_components.WishlistHeart
import com.example.mycoffeeapp.ui.theme.IvoryWhite
import com.example.mycoffeeapp.ui.theme.LightBrown
import com.example.mycoffeeapp.ui.theme.LightGray
import java.util.Locale


@Composable
fun ProductCard(
  product: Product,
  modifier: Modifier = Modifier,
  onClick: () -> Unit = {},
  onAddClick: () -> Unit = {},
  isInWishlist: Boolean = false,
  onWishlistClick: () -> Unit = {},
) {
  Card(
    onClick = onClick,
    modifier = Modifier.padding(8.dp),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(
      containerColor = Color.White
    ),
    elevation = CardDefaults.cardElevation(
      defaultElevation = 4.dp
    )
  ) {
    Column(
      modifier = Modifier
        .padding(8.dp),
    ) {
      Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f) // ảnh vuông, co giãn theo độ rộng cột
      ) {
        Image(
          painter = painterResource(product.imageUrl),
          contentDescription = product.name,
          contentScale = ContentScale.Crop,
          modifier = Modifier
              .fillMaxSize()
              .clip(RoundedCornerShape(16.dp))
        )
        Box(
          modifier = Modifier
              .align(Alignment.TopEnd)
              .padding(8.dp)          // khoảng cách từ mép ảnh (nằm ngoài nền)
            .size(32.dp)            // kích thước ô nền
            .background(
                color = LightGray.copy(alpha = 0.9f),
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            // Nhận chạm riêng cho tim, để không mở màn Detail như khi bấm vào thẻ
            .clickable(onClick = onWishlistClick),
          contentAlignment = Alignment.Center, // icon nằm giữa ô
        )
        {
          WishlistHeart(isInWishlist = isInWishlist, size = 22.dp)
        }
      }
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = product.name,
        style = MaterialTheme.typography.titleMedium.copy(
          color = Color.Black,
          fontWeight = FontWeight.Bold
        )
      )
      Text(
        text = product.description,
        style = MaterialTheme.typography.bodySmall.copy(
          color = Color.Gray,
        ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )
      Spacer(modifier = Modifier.height(4.dp))
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "$%.2f".format(Locale.US, product.price),
          style = MaterialTheme.typography.titleMedium.copy(
            color = LightBrown,
            fontWeight = FontWeight.Bold
          )
        )
        IconButton(
          onClick = onAddClick,
          modifier = Modifier.background(
            color = LightBrown,
            shape = RoundedCornerShape(8.dp)
          )
        ) {
          Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add to Cart",
            tint = IvoryWhite
          )
        }
      }

    }
  }
}