package com.example.mycoffeeapp.local_storage

import com.example.mycoffeeapp.data.local.LocalStorageService
import com.example.mycoffeeapp.model.CartItem
import com.example.mycoffeeapp.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val CART_KEY = "cart"

class CartStorage(
    private val localStorage: LocalStorageService,
) {
    private val gson = Gson()
    private val cartType = object : TypeToken<List<CartItem>>() {}.type

    // Chưa có giỏ hàng thì bắt đầu từ danh sách rỗng.
    // Sản phẩm đã có trong giỏ thì tăng count, chưa có thì thêm mới với count = 1.
    suspend fun addToCart(product: Product) {
        localStorage.updateString(CART_KEY) { currentJson ->
            val cart = currentJson
                ?.let { gson.fromJson<List<CartItem>>(it, cartType) }
                .orEmpty()

            val updatedCart = if (cart.any { it.product.id == product.id }) {
                cart.map { item ->
                    if (item.product.id == product.id) item.copy(count = item.count + 1) else item
                }
            } else {
                cart + CartItem(product = product, count = 1)
            }

            gson.toJson(updatedCart)
        }
    }
}
