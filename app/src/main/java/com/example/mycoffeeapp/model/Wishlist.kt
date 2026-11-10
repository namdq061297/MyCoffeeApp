package com.example.mycoffeeapp.model

// Danh sách id Product đã lưu, mới nhất đứng đầu. Xem docs/adr/0001-wishlist-stores-product-ids.md
data class Wishlist(val productIds: List<Int>) {

    fun toggle(productId: Int): Wishlist =
        if (productId in productIds) copy(productIds = productIds - productId)
        else copy(productIds = listOf(productId) + productIds)

    fun remove(productId: Int): Wishlist = copy(productIds = productIds - productId)

    fun contains(productId: Int): Boolean = productId in productIds

    fun products(catalog: List<Product>): List<Product> =
        productIds.mapNotNull { id -> catalog.find { it.id == id } }
}
