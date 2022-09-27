package com.example.grocerryapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var fabAdd:FloatingActionButton
    private lateinit var itemsRecyclerView: RecyclerView
    private lateinit var groceryAdapter:GroceryAdapter
    private lateinit var groceryItems: List<GroceryItem>
    private lateinit var groceryViewModel: GroceryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabAdd = findViewById(R.id.fabAddItem)
        groceryItems = listOf()
        groceryAdapter = GroceryAdapter(groceryItems){
            groceryViewModel.delete(it)
            groceryAdapter.notifyDataSetChanged()
            Toast.makeText(this@MainActivity, "Item Deleted Successfully",
                Toast.LENGTH_SHORT).show()
        }

        itemsRecyclerView = findViewById<RecyclerView?>(R.id.rvItems).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = groceryAdapter
        }
        val groceryRepository = GroceryRepository(GroceryDatabase.getDatabase(context = this))
        val groceryViewModelFactory = GroceryViewModelFactory(groceryRepository)
        groceryViewModel = ViewModelProvider(this, groceryViewModelFactory)
            .get(GroceryViewModel::class.java)

        groceryViewModel.getAllGroceryItems.observe(this, Observer {
            groceryAdapter.itemList = it
            groceryAdapter.notifyDataSetChanged()
        })

        fabAdd.setOnClickListener{
            openDialog()
        }
    }

    private fun openDialog() {
        val addDialog = Dialog(this)
        addDialog.setContentView(R.layout.grocery_add_layout)
        addDialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val etItemName = addDialog.findViewById<EditText>(R.id.etItemName)
        val etItemPrice = addDialog.findViewById<EditText>(R.id.etItemPrice)
        val etItemQuantity = addDialog.findViewById<EditText>(R.id.etItemQuantity)

        val cancelButton = addDialog.findViewById<Button>(R.id.btCancel)
        val addButton = addDialog.findViewById<Button>(R.id.btAdd)

        cancelButton.setOnClickListener {
            addDialog.dismiss()
        }
        addButton.setOnClickListener {
            val itemName = etItemName.text.toString()
            val itemQuantity = etItemQuantity.text.toString()
            val itemPrice = etItemPrice.text.toString()

            if(itemName.isNotEmpty() && itemPrice.isNotEmpty() && itemPrice.isNotEmpty()){
                val item = GroceryItem(itemPrice = itemPrice.toInt(),
                    itemName = itemName,
                    itemQuantity = itemQuantity.toInt() )

                groceryViewModel.insert(item)
                addDialog.dismiss()
                Toast.makeText(this@MainActivity, "Item Added Successfully",
                    Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this@MainActivity, "Please Enter Correct Values",
                    Toast.LENGTH_SHORT).show()
            }

        }

        addDialog.show()
    }
}