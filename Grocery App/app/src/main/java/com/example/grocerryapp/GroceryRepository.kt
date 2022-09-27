package com.example.grocerryapp

class GroceryRepository(private val db: GroceryDatabase) {

    fun getAllItems() = db.getGroceryDao().getAllItems()

    suspend fun insert(item: GroceryItem) = db.getGroceryDao().insert(item)

    suspend fun delete(item: GroceryItem) = db.getGroceryDao().delete(item)

}