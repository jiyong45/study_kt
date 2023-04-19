package com.example.recyclerview_basic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstBtn = this.findViewById<Button>(R.id.first_btn)
        firstBtn.setOnClickListener{
            val intent = Intent(this@MainActivity, MainActivity::class.java)
            startActivity(intent)
        }
        val secondBtn = this.findViewById<Button>(R.id.second_btn)
        secondBtn.setOnClickListener{
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
        }
        val thirdBtn = this.findViewById<Button>(R.id.third_btn)
        thirdBtn.setOnClickListener{
            val intent = Intent(this@MainActivity, ThirdActivity::class.java)
            startActivity(intent)
        }
    }
}