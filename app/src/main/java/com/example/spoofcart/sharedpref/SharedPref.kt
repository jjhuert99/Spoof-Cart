package com.example.spoofcart.sharedpref

interface SharedPref {
    fun getString(key: String, value: String) : List<CartItem>?

    fun addOneItem(cartItem : CartItem)

    fun addWholeList(sharedPrefKey: String)
}
