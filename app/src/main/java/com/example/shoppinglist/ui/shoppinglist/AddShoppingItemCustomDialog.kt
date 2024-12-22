package com.example.shoppinglist.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.ShoppingItem

class AddShoppingItemCustomDialog(context: Context, var addDialogListener: AddDialogListener): AppCompatDialog(context) {

    private lateinit var tvAdd: TextView
    private lateinit var tvCancel: TextView
    private lateinit var etName: EditText
    private lateinit var etAmount: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shoppingitem)
        tvAdd = findViewById(R.id.tvOk)!!
        tvCancel = findViewById(R.id.tvCancel)!!
        etName = findViewById(R.id.etName)!!
        etAmount = findViewById(R.id.etAmount)!!

        tvAdd.setOnClickListener{
            val name = etName.text.toString()
            val amount = etAmount.text.toString()

            if(name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(context, "Please enter all details", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = ShoppingItem(name, amount.toInt())
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        tvCancel.setOnClickListener {
            cancel()
        }
    }

}