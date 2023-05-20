package com.example.myfarm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import GridAdapter

class fruits : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruits)

        val gridView: GridView = findViewById(R.id.gridView)
        gridView.adapter = GridAdapter(this)
    }
}
