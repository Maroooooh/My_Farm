package com.example.myfarm

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.StringBuilder
import GridAdapter
import GridAdapter2
class your_orders: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_orders)

        val clrButton = findViewById<Button>(R.id.btn)
        val clr2Button = findViewById<Button>(R.id.btnno)
        val fruitsTextView: TextView = findViewById(R.id.fruitsorder)
        val sharedPreferences = getSharedPreferences("MyFarm", Context.MODE_PRIVATE)
        val orderedFruits = sharedPreferences.all

        val fruitsStringBuilder = StringBuilder()
        var total = 0.0

        val gridAdapter = GridAdapter(this)
        val gridAdapter2 = GridAdapter2(this)
        for ((fruit, quantity) in orderedFruits) {
            if (fruit !=""){
            val line = "$fruit   ,    Quantity:    $quantity\n"

            fruitsStringBuilder.append(line)}
        }
        for ((fruit, quantity) in orderedFruits) {
            if (fruit != "" ){
            val q = quantity.toString().toLong()  // Convert quantity to Double
            val p = gridAdapter.check(fruit)
            val p2 = gridAdapter2.check(fruit)

            if (p != 0.0) {
                total += q * p
            } else {
                total += q * p2
            }
            }
        }
        var toot = total
        val pricee: TextView = findViewById(R.id.tot)
        pricee.text = toot.toString()
        val fruitsText = fruitsStringBuilder.toString()
        fruitsTextView.text = fruitsText

        clrButton.setOnClickListener {
            // Clear the order
            GridAdapter.maporder.clear()
            fruitsTextView.text = ""
            pricee.text = "0.0"

            // Clear the saved order data in shared preferences
            val sharedPreferences = getSharedPreferences("MyFarm", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()

            Toast.makeText(this, "Your order is going now", Toast.LENGTH_SHORT).show()
        }


        clr2Button.setOnClickListener {
            GridAdapter.maporder.clear()
            fruitsTextView.text = ""
            pricee.text = "0.0"
            val sharedPreferences = getSharedPreferences("MyFarm", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            Toast.makeText(this, "Your order is canceled now", Toast.LENGTH_SHORT).show()
        }
    }
}
