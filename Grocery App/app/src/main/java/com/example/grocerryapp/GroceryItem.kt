package com.example.grocerryapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grocery_table")
data class GroceryItem(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id:Int?= null,

    @ColumnInfo(name = "itemPrice")
    var itemPrice:Int,

    @ColumnInfo(name = "itemQuantity")
    var itemQuantity:Int,

    @ColumnInfo(name = "itemName")
    var itemName:String
)
