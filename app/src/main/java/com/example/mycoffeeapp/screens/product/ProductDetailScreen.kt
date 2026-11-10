package com.example.mycoffeeapp.screens.product

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.mycoffeeapp.R
import com.example.mycoffeeapp.mock.MockData
import com.example.mycoffeeapp.model.CoffeeSize
import com.example.mycoffeeapp.model.Product
import com.example.mycoffeeapp.screens.ui_components.WishlistHeart
import com.example.mycoffeeapp.ui.theme.CharcoalGray
import com.example.mycoffeeapp.ui.theme.LightBrown
import java.util.Locale

private val DetailBackground = Color(0xFFF9F2ED)
private val SecondaryText = Color(0xFFA2A2A2)
private val SelectedSizeBackground = Color(0xFFFFF5EE)
private val BorderGray = Color(0xFFE3E3E3)

@Composable
fun ProductDetailScreen(
  product: Product,
  onBackClick: () -> Unit = {},
  isInWishlist: Boolean = false,
  onWishlistClick: () -> Unit = {},
  onAddToCart: (Product, CoffeeSize) -> Unit = { _, _ -> },
) {
  var selectedSize by remember { mutableStateOf(CoffeeSize.M) }

  Scaffold(
    containerColor = DetailBackground,
    topBar = {
      DetailTopBar(
        onBackClick = onBackClick,
        isInWishlist = isInWishlist,
        onWishlistClick = onWishlistClick,
      )
    },
    bottomBar = {
      DetailBottomBar(
        price = product.price,
        onAddToCart = { onAddToCart(product, selectedSize) },
      )
    },
  ) { innerPadding ->
    Column(
      modifier = Modifier
          .fillMaxSize()
          .padding(innerPadding)
          .verticalScroll(rememberScrollState())
          .padding(horizontal = 24.dp),
    ) {
      Spacer(modifier = Modifier.height(8.dp))

      // Ảnh sản phẩm
      Image(
        painter = painterResource(id = product.imageUrl),
        contentDescription = product.name,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp)),
      )

      Spacer(modifier = Modifier.height(16.dp))

      // Tên + loại (Ice / Hot) + icon hạt cà phê
      Text(
        text = product.name,
        color = CharcoalGray,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
      )
      Spacer(modifier = Modifier.height(4.dp))
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Text(text = "Ice / Hot", color = SecondaryText, fontSize = 12.sp)
        Box(
          modifier = Modifier
              .size(40.dp)
              .background(SelectedSizeBackground, RoundedCornerShape(12.dp)),
          contentAlignment = Alignment.Center,
        ) {
          Image(
            painter = painterResource(id = R.drawable.default_bean),
            contentDescription = "Coffee bean",
            modifier = Modifier.size(20.dp),
          )
        }
      }

      HorizontalDivider(
        modifier = Modifier.padding(vertical = 16.dp),
        color = BorderGray,
      )

      // Mô tả
      Text(
        text = "Description",
        color = CharcoalGray,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
      )
      Spacer(modifier = Modifier.height(8.dp))
      Text(
        text = product.description,
        color = SecondaryText,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
      )

      Spacer(modifier = Modifier.height(24.dp))

      // Chọn size
      Text(
        text = "Size",
        color = CharcoalGray,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
      )
      Spacer(modifier = Modifier.height(12.dp))
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
      ) {
        CoffeeSize.entries.forEach { size ->
          SizeOption(
            label = size.label,
            selected = size == selectedSize,
            onClick = { selectedSize = size },
            modifier = Modifier.weight(1f),
          )
        }
      }

      Spacer(modifier = Modifier.height(24.dp))
    }
  }
}

@Composable
private fun DetailTopBar(
  onBackClick: () -> Unit,
  isInWishlist: Boolean,
  onWishlistClick: () -> Unit,
) {
  Box(
    modifier = Modifier
        .fillMaxWidth()
        .statusBarsPadding()
        .padding(horizontal = 12.dp, vertical = 8.dp),
  ) {
    IconButton(onClick = onBackClick, modifier = Modifier.align(Alignment.CenterStart)) {
      Icon(
        painter = painterResource(id = R.drawable.regular_outline_arrow_left),
        contentDescription = "Back",
        tint = CharcoalGray,
        modifier = Modifier.size(24.dp),
      )
    }
    Text(
      text = "Detail",
      color = CharcoalGray,
      fontSize = 18.sp,
      fontWeight = FontWeight.SemiBold,
      modifier = Modifier.align(Alignment.Center),
    )
    IconButton(onClick = onWishlistClick, modifier = Modifier.align(Alignment.CenterEnd)) {
      WishlistHeart(isInWishlist = isInWishlist, outlineTint = CharcoalGray)
    }
  }
}

@Composable
private fun SizeOption(
  label: String,
  selected: Boolean,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val shape = RoundedCornerShape(12.dp)
  Box(
    modifier = modifier
        .height(44.dp)
        .clip(shape)
        .background(if (selected) SelectedSizeBackground else Color.White)
        .border(
            BorderStroke(1.dp, if (selected) LightBrown else BorderGray),
            shape,
        )
        .clickable(onClick = onClick),
    contentAlignment = Alignment.Center,
  ) {
    Text(
      text = label,
      color = if (selected) LightBrown else CharcoalGray,
      fontSize = 14.sp,
      fontWeight = FontWeight.Medium,
    )
  }
}

@Composable
private fun DetailBottomBar(
  price: Double,
  onAddToCart: () -> Unit,
) {
  Row(
    modifier = Modifier
        .fillMaxWidth()
        .background(Color.White, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
        .navigationBarsPadding()
        .padding(horizontal = 24.dp, vertical = 16.dp),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Column {
      Text(text = "Price", color = SecondaryText, fontSize = 14.sp)
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = "$ %.2f".format(Locale.US, price),
        color = LightBrown,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
      )
    }
    Spacer(modifier = Modifier.width(32.dp))
    Button(
      onClick = onAddToCart,
      modifier = Modifier
          .weight(1f)
          .height(56.dp),
      shape = RoundedCornerShape(16.dp),
      colors = ButtonDefaults.buttonColors(containerColor = LightBrown),
    ) {
      Text(
        text = "Add To Cart",
        color = Color.White,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun ProductDetailScreenPreview() {
  ProductDetailScreen(product = MockData.productMockData.first())
}
