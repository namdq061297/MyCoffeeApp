package com.example.mycoffeeapp.screens.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.mycoffeeapp.R
import com.example.mycoffeeapp.mock.MockData
import com.example.mycoffeeapp.model.CartItem
import com.example.mycoffeeapp.model.CoffeeSize
import com.example.mycoffeeapp.navigation.Routes
import com.example.mycoffeeapp.screens.ui_components.BottomAppNavBar
import com.example.mycoffeeapp.ui.theme.CharcoalGray
import com.example.mycoffeeapp.ui.theme.LightBrown
import java.util.Locale

private val OrderBackground = Color(0xFFF9F2ED)
private val SectionBackground = Color(0xFFE8E8E8)
private val SecondaryText = Color(0xFF808080)
private const val DELIVERY_FEE = 1.0

private fun formatPrice(value: Double) = "$ %.2f".format(Locale.US, value)

@Composable
fun OrderScreen(
  items: List<CartItem>,
  onBackClick: () -> Unit = {},
  onChangeCount: (CartItem, Int) -> Unit = { _, _ -> },
  onPlaceOrder: () -> Unit = {},
  onBottomNavClick: (String) -> Unit = {},
) {
  val price = items.sumOf { it.product.price * it.count }

  Scaffold(
    containerColor = OrderBackground,
    bottomBar = {
      BottomAppNavBar(currentRoute = Routes.ORDER, onItemClick = onBottomNavClick)
    },
  ) { innerPadding ->
    LazyColumn(
      modifier = Modifier
          .fillMaxSize()
          .padding(innerPadding),
      verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
      item { OrderHeader(onBackClick = onBackClick) }

      if (items.isEmpty()) {
        item {
          Text(
            text = "Your cart is empty",
            color = SecondaryText,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp),
          )
        }
      } else {
        // Khóa gồm cả size vì cùng một sản phẩm có thể có nhiều dòng theo size
        items(items, key = { "${it.product.id}_${it.size}" }) { item ->
          OrderItemRow(
            item = item,
            onDecrease = { onChangeCount(item, -1) },
            onIncrease = { onChangeCount(item, 1) },
          )
        }
        item {
          PaymentSection(price = price, onPlaceOrder = onPlaceOrder)
        }
      }
    }
  }
}

@Composable
private fun OrderHeader(onBackClick: () -> Unit) {
  Column(modifier = Modifier.padding(horizontal = 4.dp)) {
    Box(modifier = Modifier.fillMaxWidth()) {
      IconButton(onClick = onBackClick, modifier = Modifier.align(Alignment.CenterStart)) {
        Icon(
          painter = painterResource(id = R.drawable.regular_outline_arrow_left),
          contentDescription = "Back",
          tint = CharcoalGray,
          modifier = Modifier.size(24.dp),
        )
      }
      Text(
        text = "Order",
        color = CharcoalGray,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.align(Alignment.Center),
      )
    }
    Text(
      text = "Deliver",
      color = LightBrown,
      fontSize = 22.sp,
      fontWeight = FontWeight.Bold,
      modifier = Modifier.padding(start = 12.dp, top = 4.dp),
    )
  }
}

@Composable
private fun OrderItemRow(
  item: CartItem,
  onDecrease: () -> Unit,
  onIncrease: () -> Unit,
) {
  Row(
    modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
        .background(SectionBackground, RoundedCornerShape(12.dp))
        .padding(10.dp),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Image(
      painter = painterResource(id = item.product.imageUrl),
      contentDescription = item.product.name,
      contentScale = ContentScale.Crop,
      modifier = Modifier
          .size(56.dp)
          .clip(RoundedCornerShape(8.dp)),
    )
    Spacer(modifier = Modifier.width(12.dp))
    Column(modifier = Modifier.weight(1f)) {
      Text(
        text = "${item.product.name} (${item.size.label})",
        color = CharcoalGray,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
      )
      Text(
        text = item.product.description,
        color = SecondaryText,
        fontSize = 12.sp,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
      )
    }
    Spacer(modifier = Modifier.width(8.dp))
    CountButton(symbol = "−", description = "Decrease quantity", onClick = onDecrease)
    Text(
      text = item.count.toString(),
      color = CharcoalGray,
      fontSize = 14.sp,
      modifier = Modifier.padding(horizontal = 10.dp),
    )
    CountButton(symbol = "+", description = "Increase quantity", onClick = onIncrease)
  }
}

@Composable
private fun CountButton(
  symbol: String,
  description: String,
  onClick: () -> Unit,
) {
  Box(
    modifier = Modifier
        .size(28.dp)
        .clip(CircleShape)
        .background(LightBrown.copy(alpha = 0.2f))
        .clickable(onClickLabel = description, onClick = onClick),
    contentAlignment = Alignment.Center,
  ) {
    Text(text = symbol, color = LightBrown, fontSize = 18.sp, fontWeight = FontWeight.Bold)
  }
}

@Composable
private fun PaymentSection(
  price: Double,
  onPlaceOrder: () -> Unit,
) {
  val total = price + DELIVERY_FEE

  Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
    Text(
      text = "Payment Summary",
      color = CharcoalGray,
      fontSize = 16.sp,
      fontWeight = FontWeight.SemiBold,
    )
    Spacer(modifier = Modifier.height(12.dp))
    SummaryRow(label = "Price", value = formatPrice(price))
    Spacer(modifier = Modifier.height(8.dp))
    SummaryRow(label = "Delivery Fee", value = formatPrice(DELIVERY_FEE))
    Spacer(modifier = Modifier.height(16.dp))

    Column(
      modifier = Modifier
          .fillMaxWidth()
          .background(SectionBackground, RoundedCornerShape(12.dp))
          .padding(16.dp),
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
          painter = painterResource(id = R.drawable.wallet),
          contentDescription = null,
          modifier = Modifier.size(24.dp),
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = "Online",
            color = CharcoalGray,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
          )
          Text(text = formatPrice(total), color = LightBrown, fontSize = 13.sp)
        }
        Icon(
          painter = painterResource(id = R.drawable.regular_outline_arrow_down),
          contentDescription = null,
          tint = CharcoalGray,
          modifier = Modifier.size(20.dp),
        )
      }
      Spacer(modifier = Modifier.height(16.dp))
      Button(
        onClick = onPlaceOrder,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = LightBrown),
      ) {
        Text(text = "Place Order", color = Color.White, fontSize = 16.sp)
      }
    }
  }
}

@Composable
private fun SummaryRow(label: String, value: String) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
  ) {
    Text(text = label, color = CharcoalGray, fontSize = 14.sp)
    Text(text = value, color = CharcoalGray, fontSize = 14.sp)
  }
}

@Preview(showBackground = true)
@Composable
private fun OrderScreenPreview() {
  OrderScreen(
    items = MockData.productMockData.take(3).map { product ->
      CartItem(product = product, size = CoffeeSize.M, count = 1)
    },
  )
}
