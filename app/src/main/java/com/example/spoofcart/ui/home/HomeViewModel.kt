package com.example.spoofcart.ui.home

import android.app.Application
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spoofcart.network.ServiceResult
import com.example.spoofcart.network.ShoppingItem
import com.example.spoofcart.network.ShoppingRepo
import com.example.spoofcart.sharedpref.CartItem
import com.example.spoofcart.sharedpref.SharedPref
import com.example.spoofcart.sharedpref.SharedPrefImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val app: Application,
    private val ShoppingRepo: ShoppingRepo,
    private val dispatcher: Dispatchers,
    private val sharedPrefs: SharedPref
) : ViewModel() {

    var checkAdd = false

    private val _navYet = MutableLiveData<Boolean>()
    val navYet: LiveData<Boolean> = _navYet

    enum class SpoofStatus { ERROR, DONE }

    private val _status = MutableLiveData<SpoofStatus>()
    val status: LiveData<SpoofStatus> = _status

    private val _post = MutableLiveData<List<ShoppingItem>?>()
    val post: MutableLiveData<List<ShoppingItem>?> = _post

    fun doneNav(){
        _navYet.value = false
    }
    fun justNav(){
        _navYet.value = true
    }

    fun addOneItemSharedPrefs(item: CartItem){
        sharedPrefs.addOneItem(item)
    }

    fun addWholeListSharedPrefs(name: String){
        sharedPrefs.addWholeList(name)
    }

    init{
        getShoppingItems()
    }

    private fun getShoppingItems(){
        viewModelScope.launch(dispatcher.IO){
            when(val response = ShoppingRepo.getShoppingItems()){
                is ServiceResult.Success -> {
                    _post.postValue(response.data)
                    _status.postValue(SpoofStatus.DONE)
                }
                is ServiceResult.Error -> {
                    _status.postValue(SpoofStatus.ERROR)
                }
                else -> {
                    _status.postValue(SpoofStatus.ERROR)
                }
            }
        }
    }
}
