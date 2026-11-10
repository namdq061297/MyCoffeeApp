package com.example.mycoffeeapp.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class WishlistTest {

    @Test
    fun `toggling a new product puts it first`() {
        val wishlist = Wishlist(productIds = listOf(1, 2))

        assertEquals(listOf(3, 1, 2), wishlist.toggle(3).productIds)
    }

    @Test
    fun `toggling a saved product removes it`() {
        val wishlist = Wishlist(productIds = listOf(3, 1, 2))

        assertEquals(listOf(3, 2), wishlist.toggle(1).productIds)
    }

    @Test
    fun `removing a saved product drops it and keeps it removed`() {
        val wishlist = Wishlist(productIds = listOf(3, 1, 2))

        assertEquals(listOf(3, 2), wishlist.remove(1).productIds)
        assertEquals(listOf(3, 2), wishlist.remove(1).remove(1).productIds)
    }

    @Test
    fun `contains reports whether a product is saved`() {
        val wishlist = Wishlist(productIds = listOf(3, 1))

        assertTrue(wishlist.contains(1))
        assertFalse(wishlist.contains(2))
    }

    @Test
    fun `products follow wishlist order and skip ids missing from the catalog`() {
        val latte = product(id = 1, name = "Latte")
        val mocha = product(id = 2, name = "Mocha")
        val espresso = product(id = 3, name = "Espresso")
        val wishlist = Wishlist(productIds = listOf(3, 99, 1))

        assertEquals(listOf(espresso, latte), wishlist.products(listOf(latte, mocha, espresso)))
    }

    private fun product(id: Int, name: String) = Product(
        id = id,
        name = name,
        description = "",
        price = 1.0,
        imageUrl = 0,
        category = "Coffee",
    )
}
