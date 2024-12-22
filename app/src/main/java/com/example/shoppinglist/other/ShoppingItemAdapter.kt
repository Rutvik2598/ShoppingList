package com.example.shoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.ShoppingItem
import com.example.shoppinglist.ui.shoppinglist.ShoppingViewModel
import org.w3c.dom.Text

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    inner class ShoppingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView
        val amountTextView: TextView
        val imagePlus: ImageView
        val imageMinus: ImageView
        val imageDelete: ImageView

        init {
            nameTextView = itemView.findViewById(R.id.tvName)
            amountTextView = itemView.findViewById(R.id.tvAmount)
            imagePlus = itemView.findViewById(R.id.ivPlus)
            imageMinus = itemView.findViewById(R.id.ivMinus)
            imageDelete = itemView.findViewById(R.id.ivDelete)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currShoppingItem = items[position]

        holder.apply {
            nameTextView.text = currShoppingItem.name
            amountTextView.text = "${currShoppingItem.amount}"

            imagePlus.setOnClickListener{
                currShoppingItem.amount++
                viewModel.upsert(currShoppingItem)
            }
            imageMinus.setOnClickListener{
                if (currShoppingItem.amount > 0){
                    currShoppingItem.amount--
                    viewModel.upsert(currShoppingItem)
                }
            }
            imageDelete.setOnClickListener{
                viewModel.delete(currShoppingItem)
            }
        }
    }

}