package com.example.mycoffeeapp.local_storage

import com.example.mycoffeeapp.data.local.LocalStorageService
import com.example.mycoffeeapp.model.CartItem
import com.example.mycoffeeapp.model.CoffeeSize
import com.example.mycoffeeapp.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val CART_KEY = "cart"

class CartStorage(
    private val localStorage: LocalStorageService,
) {
    private val gson = Gson()
    private val cartType = object : TypeToken<List<CartItem>>() {}.type

    // Danh sách trong giỏ, tự phát giá trị mới mỗi khi dữ liệu thay đổi
    val cartItems: Flow<List<CartItem>> = localStorage.getString(CART_KEY).map(::parseCart)

    // Tăng hoặc giảm số lượng của một dòng; số lượng về 0 thì xóa dòng đó khỏi giỏ
    suspend fun changeCount(target: CartItem, delta: Int) {
        localStorage.updateString(CART_KEY) { currentJson ->
            val updatedCart = parseCart(currentJson).mapNotNull { item ->
                val isTarget = item.product.id == target.product.id && item.size == target.size
                when {
                    !isTarget -> item
                    item.count + delta > 0 -> item.copy(count = item.count + delta)
                    else -> null
                }
            }
            gson.toJson(updatedCart)
        }
    }

    // Chưa có giỏ hàng thì bắt đầu từ danh sách rỗng.
    // Cùng sản phẩm và cùng size thì tăng count, khác size thì là một dòng mới với count = 1.
    suspend fun addToCart(product: Product, size: CoffeeSize) {
        localStorage.updateString(CART_KEY) { currentJson ->
            val cart = parseCart(currentJson)

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

    // Gson bỏ qua kiểm tra non-null của Kotlin: dòng lưu trước khi có field size sẽ có size = null.
    // Bỏ các dòng đó để màn hình không bị crash khi đọc size.
    @Suppress("SENSELESS_COMPARISON")
    private fun parseCart(json: String?): List<CartItem> =
        json?.let { gson.fromJson<List<CartItem>>(it, cartType) }
            .orEmpty()
            .filter { it.size != null }
}
