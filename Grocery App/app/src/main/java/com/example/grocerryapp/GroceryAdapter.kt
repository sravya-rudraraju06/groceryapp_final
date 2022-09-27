package com.example.grocerryapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GroceryAdapter(var itemList: List<GroceryItem>, val clickListener: (GroceryItem) -> Unit):
RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder>(){
    inner class GroceryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private var itemName:TextView = itemView.findViewById(R.id.tvItemName)
        private var itemQuantity:TextView = itemView.findViewById(R.id.tvQuantity)
        private var itemRate:TextView = itemView.findViewById(R.id.tvRate)
        private var itemTotalCost:TextView = itemView.findViewById(R.id.tvTotalCost)
        private var itemAmount:TextView = itemView.findViewById(R.id.tvAmount)
        private var itemDelete:ImageView = itemView.findViewById(R.id.ivDelete)

        fun bind(currentItem: GroceryItem) {
            itemName.text = currentItem.itemName
            itemQuantity.text = currentItem.itemQuantity.toString()
            itemRate.text = "Rs. ${currentItem.itemPrice}"
            val totalAmount = currentItem.itemPrice * currentItem.itemQuantity
            itemAmount.text = "Rs. $totalAmount"
            itemDelete.setOnClickListener { clickListener(currentItem) }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grocery_item, parent, false)
        return GroceryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}