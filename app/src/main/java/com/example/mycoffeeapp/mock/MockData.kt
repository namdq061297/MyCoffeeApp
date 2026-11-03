package com.example.mycoffeeapp.mock

import com.example.mycoffeeapp.model.Product
object MockData {
    val productMockData = listOf(
        Product(
            id = 1,
            name = "Cappuccino",
            description = "A classic Italian coffee drink made with espresso, steamed milk, and a layer of frothed milk.",
            price = 3.99,
            imageUrl = "https://images.unsplash.com/photo-1601924582975-4c8e6f0b9f1d",
            category = "Coffee"
        ),
        Product(
            id = 2,
            name = "Latte",
            description = "A creamy coffee drink made with espresso and steamed milk, often topped with a small amount of foam.",
            price = 4.49,
            imageUrl = "https://images.unsplash.com/photo-1589927986089-35812388d1a0",
            category = "Coffee"
        ),
        Product(
            id = 3,
            name = "Espresso",
            description = "A strong and concentrated coffee made by forcing hot water through finely-ground coffee beans.",
            price = 2.99,
            imageUrl = "https://images.unsplash.com/photo-1511920170033-f8396924c348",
            category = "Coffee"
        ),
        Product(
            id = 4,
            name = "Mocha",
            description = "A chocolate-flavored coffee drink made with espresso, steamed milk, and chocolate syrup.",
            price = 4.99,
            imageUrl = "https://images.unsplash.com/photo-1601924582975-4c8e6f0b9f1d",
            category = "Coffee"
        ),
    )
}