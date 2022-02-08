package com.example.spoofcart.di

import com.example.spoofcart.network.RetrofitFactory
import com.example.spoofcart.network.ShoppingEndPoints
import com.example.spoofcart.network.ShoppingRepo
import com.example.spoofcart.network.ShoppingRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ShoppingItemsModule {

    private const val BASE_URL = "https://fakestoreapi.com/"

    @Singleton
    @Provides
    fun provideShoppingRetrofit(): ShoppingEndPoints{
        return RetrofitFactory.retrofitProvider(
            ShoppingEndPoints::class.java,
            BASE_URL
        )
    }

    @Singleton
    @Provides
    fun provideShoppingRepo(dispatcher: Dispatchers, retroObject: ShoppingEndPoints): ShoppingRepo{
        return ShoppingRepoImpl(dispatcher, retroObject)
    }
}
