package com.example.spoofcart.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShoppingRepoImpl @Inject constructor(
    private val dispatcher: Dispatchers,
    private val retroObject: ShoppingEndPoints
): ShoppingRepo {
    override suspend fun getShoppingItems()
    : ServiceResult<List<ShoppingItem>?> {
        return withContext(dispatcher.IO){
            val dataResponse = retroObject.getShoppingItems()
            if(dataResponse.isSuccessful){
                ServiceResult.Success(dataResponse.body())
            } else{
                ServiceResult.Error(Exception(dataResponse.errorBody().toString()))
            }
        }
    }
}
