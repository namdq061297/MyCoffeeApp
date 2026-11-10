package com.example.mycoffeeapp.local_storage

import com.example.mycoffeeapp.data.local.LocalStorageService
import com.example.mycoffeeapp.model.Wishlist
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val WISHLIST_KEY = "wishlist"

// Lưu Wishlist dưới dạng JSON của danh sách id Product. Xem docs/adr/0001-wishlist-stores-product-ids.md
class WishlistStorage(
    private val localStorage: LocalStorageService,
) {
    private val gson = Gson()
    private val idsType = object : TypeToken<List<Int>>() {}.type

    val wishlist: Flow<Wishlist> = localStorage.getString(WISHLIST_KEY).map(::parse)

    suspend fun toggle(productId: Int) = update { it.toggle(productId) }

    suspend fun remove(productId: Int) = update { it.remove(productId) }

    private suspend fun update(change: (Wishlist) -> Wishlist) {
        localStorage.updateString(WISHLIST_KEY) { json ->
            gson.toJson(change(parse(json)).productIds)
        }
    }

    private fun parse(json: String?): Wishlist {
        val ids: List<Int>? = try {
            json?.let { gson.fromJson(it, idsType) }
        } catch (e: JsonSyntaxException) {
            null
        }
        return Wishlist(productIds = ids.orEmpty())
    }
}
