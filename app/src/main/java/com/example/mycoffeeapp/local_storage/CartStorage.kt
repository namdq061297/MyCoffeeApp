package com.example.mycoffeeapp.local_storage

import com.example.mycoffeeapp.data.local.LocalStorageService
import com.example.mycoffeeapp.model.CartItem
import com.example.mycoffeeapp.model.CoffeeSize
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
    // Cùng sản phẩm và cùng size thì tăng count, khác size thì là một dòng mới với count = 1.
    suspend fun addToCart(product: Product, size: CoffeeSize) {
        localStorage.updateString(CART_KEY) { currentJson ->
            val cart = currentJson
                ?.let { gson.fromJson<List<CartItem>>(it, cartType) }
                .orEmpty()

            val isSameItem = { item: CartItem -> item.product.id == product.id && item.size == size }

            val updatedCart = if (cart.any(isSameItem)) {
                cart.map { item ->
                    if (isSameItem(item)) item.copy(count = item.count + 1) else item
                }
            } else {
                cart + CartItem(product = product, size = size, count = 1)
            }
            println("Updated cart: $updatedCart")
            gson.toJson(updatedCart)
        }
    }
}
