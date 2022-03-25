package com.example.spoofcart.sharedpref

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPrefImpl(val context: Context) : SharedPref {

    val ourList = mutableListOf<CartItem>()
    val gson = Gson()

    private val prefKey = "NEW_FILE"
    private var sharedPreferences: EncryptedSharedPreferences

    init {
        sharedPreferences = initializeSharedPref()
    }

    val editor = sharedPreferences.edit()

    private fun initializeSharedPref(): EncryptedSharedPreferences {
        val masterKey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        return EncryptedSharedPreferences.create(
            prefKey,
            masterKey,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        ) as EncryptedSharedPreferences
    }

    override fun getString(key: String, value: String): List<CartItem>? {
        val gson = Gson()
        val json = sharedPreferences?.getString("ShoppingList", "")
        val t = object : TypeToken<ArrayList<CartItem>>() {}.type
        val list = gson.fromJson<List<CartItem>>(json, t)

        return list
    }

    override fun addOneItem(cartItem: CartItem) {
        ourList.add(cartItem)
    }

    override fun addWholeList(sharedPrefKey: String) {
        val json = gson.toJson(ourList)
        editor?.putString("ShoppingList", json)
        editor?.apply()
    }
}
