package com.example.grocerryapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GroceryDao {
    @Query("SELECT * FROM grocery_table")
    fun getAllItems():LiveData<List<GroceryItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: GroceryItem)

    @Delete
    suspend fun delete(item: GroceryItem)
}