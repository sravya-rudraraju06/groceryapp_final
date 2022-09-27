package com.example.grocerryapp

import android.content.ClipData
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GroceryViewModel(private val repository: GroceryRepository):ViewModel() {

    fun insert(item: GroceryItem) = viewModelScope.launch {
        repository.insert(item)
    }

    fun delete(item: GroceryItem) = viewModelScope.launch {
        repository.delete(item)
    }

    val getAllGroceryItems = repository.getAllItems()

    //grocer

}

class GroceryViewModelFactory(private val repository: GroceryRepository):
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GroceryViewModel(repository) as T
    }
}