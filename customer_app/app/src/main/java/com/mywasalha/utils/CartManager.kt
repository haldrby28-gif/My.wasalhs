package com.mywasalha.utils

import com.mywasalha.models.CartItem
import com.mywasalha.models.Product

object CartManager {

    private val cartItems = mutableListOf<CartItem>()

    fun addProduct(product: Product) {

        val item = cartItems.find { it.productId == product.id }

        if (item != null) {
            item.quantity++
        } else {

            cartItems.add(
                CartItem(
                    productId = product.id,
                    name = product.name,
                    price = product.price,
                    quantity = 1
                )
            )
        }
    }

    fun getItems(): MutableList<CartItem> = cartItems

    fun getTotal(): Double {

        var total = 0.0

        cartItems.forEach {
            total += it.price * it.quantity
        }

        return total
    }

    fun clear() {
        cartItems.clear()
    }
}
