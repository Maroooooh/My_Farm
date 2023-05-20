package com.example.myfarm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import GridAdapter2

class vegetables : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vegetables)
        val gridView: GridView = findViewById(R.id.gridView2)
        gridView.adapter = GridAdapter2(this)
    }
}
