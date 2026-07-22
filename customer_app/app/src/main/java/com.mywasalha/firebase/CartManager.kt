
package com.mywasalha.firebase


import com.mywasalha.models.CartItem



object CartManager {


    private val cartItems =
        mutableListOf<CartItem>()



    fun addItem(item: CartItem) {


        val existingItem =
            cartItems.find {
                it.id == item.id
            }



        if (existingItem != null) {


            existingItem.quantity += 1


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


        var total = 0.0


        for (item in cartItems) {


            total +=
                item.price * item.quantity

        }


        return total

    }




    fun clearCart() {


        cartItems.clear()

    }

}
