package com.example.myfarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val t = findViewById<TextView>(R.id.fruit)
        t.setOnClickListener {
            val intent = Intent(this,fruits ::class.java)
            startActivity(intent)
        }
        val tt = findViewById<TextView>(R.id.veg)
        tt.setOnClickListener {
            val intent = Intent(this, vegetables::class.java)
            startActivity(intent)
        }
        val ttt = findViewById<TextView>(R.id.order)
        ttt.setOnClickListener {
            try {
                val intent = Intent(this, your_orders::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Failed to open your_orders activity: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }


    }
}
