package com.example.spoofcart.network

import retrofit2.Response
import retrofit2.http.GET

interface ShoppingEndPoints {

    @GET(".")
    suspend fun getShoppingItems() : Response<List<ShoppingItem>>
}
