package com.waselha.customer.firebase

import com.waselha.customer.models.CartItem

object CartManager {

    private val cartItems = mutableListOf<CartItem>()

    fun addItem(item: CartItem) {

        val existingItem = cartItems.find {
            it.id == item.id
        }

        if (existingItem != null) {
            existingItem.quantity++
        } else {
            cartItems.add(item)
        }
    }

    fun removeItem(itemId: String) {
        cartItems.removeAll {
            it.id == itemId
        }
    }

    fun getItems(): MutableList<CartItem> {
        return cartItems
    }

    fun getTotal(): Double {
        return cartItems.sumOf {
            it.price * it.quantity
        }
    }

    fun clearCart() {
        cartItems.clear()
    }

    fun getItemCount(): Int {
        return cartItems.sumOf {
            it.quantity
        }
    }

    fun isEmpty(): Boolean {
        return cartItems.isEmpty()
    }
}
