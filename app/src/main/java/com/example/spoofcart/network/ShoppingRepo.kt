package com.example.spoofcart.network

interface ShoppingRepo {
    suspend fun getShoppingItems(): ServiceResult<List<ShoppingItem>?>
}
