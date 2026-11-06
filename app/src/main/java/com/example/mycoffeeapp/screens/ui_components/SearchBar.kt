package com.example.mycoffeeapp.screens.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycoffeeapp.R
import com.example.mycoffeeapp.ui.theme.LightBrown

private val SearchFieldColor = Color(0xFF2A2A2A)
private val PlaceholderColor = Color(0xFF989898)

@Composable
fun SearchBar(
  query: String,
  onQueryChange: (String) -> Unit,
  onFilterClick: () -> Unit,
  modifier: Modifier = Modifier,
  placeholder: String = "Search coffee",
) {
  val shape = RoundedCornerShape(12.dp)

  TextField(
    value = query,
    onValueChange = onQueryChange,
    modifier = modifier.fillMaxWidth(),
    singleLine = true,
    shape = shape,
    textStyle = TextStyle(fontSize = 14.sp),
    placeholder = { Text(text = placeholder, fontSize = 14.sp) },
    leadingIcon = {
      Icon(
        painter = painterResource(id = R.drawable.regular_outline_search),
        contentDescription = "Search",
        modifier = Modifier.size(20.dp),
      )
    },
    trailingIcon = {
      IconButton(
        onClick = onFilterClick,
        modifier = Modifier
            .padding(end = 6.dp)
            .size(44.dp)
            .clip(shape)
            .background(LightBrown),
      ) {
        Icon(
          painter = painterResource(id = R.drawable.regular_outline_filter),
          contentDescription = "Filter",
          tint = Color.White,
          modifier = Modifier.size(24.dp),
        )
      }
    },
    colors = TextFieldDefaults.colors(
      focusedContainerColor = SearchFieldColor,
      unfocusedContainerColor = SearchFieldColor,
      focusedTextColor = Color.White,
      unfocusedTextColor = Color.White,
      focusedPlaceholderColor = PlaceholderColor,
      unfocusedPlaceholderColor = PlaceholderColor,
      focusedLeadingIconColor = Color.White,
      unfocusedLeadingIconColor = Color.White,
      cursorColor = LightBrown,
      // Ẩn đường gạch chân mặc định của TextField
      focusedIndicatorColor = Color.Transparent,
      unfocusedIndicatorColor = Color.Transparent,
      disabledIndicatorColor = Color.Transparent,
    ),
  )
}

@Preview(showBackground = true, backgroundColor = 0xFF1F1F1F)
@Composable
private fun SearchBarPreview() {
  var query by remember { mutableStateOf("") }
  Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
    SearchBar(
      query = query,
      onQueryChange = { query = it },
      onFilterClick = { },
    )
  }
}
