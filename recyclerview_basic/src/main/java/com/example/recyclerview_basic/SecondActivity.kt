package com.example.recyclerview_basic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class SecondActivity : AppCompatActivity() {

    val jsonString by lazy { getJsonDataFromRaw()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val firstBtn = this.findViewById<Button>(R.id.first_btn)
        firstBtn.setOnClickListener{
            val intent = Intent(this@SecondActivity, MainActivity::class.java)
            startActivity(intent)
        }
        val secondBtn = this.findViewById<Button>(R.id.second_btn)
        secondBtn.setOnClickListener{
            val intent = Intent(this@SecondActivity, SecondActivity::class.java)
            startActivity(intent)
        }
        val thirdBtn = this.findViewById<Button>(R.id.third_btn)
        thirdBtn.setOnClickListener{
            val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
            startActivity(intent)
        }

        Log.d("gdgdgd", "in Second")
        val data = Gson().fromJson(jsonString, Test::class.java)
        val memberList = data
        Log.d("gdgdgd", "in data ${data.member}")
        Log.d("gdgdgd", "in data ${data.member.size}")
        Log.d("gdgdgd", "in data ${data.member.lastIndex}")


        data.member.add(
           Item(lastStatus = 1)
        )
        Log.d("gdgdgd", "in data ${data.member[50].userName}")
        Log.d("gdgdgd", "in data ${data.member[50].lastStatus}")

        Log.d("gdgdgd", "in data2 ${data.member.size}")
        //val mAdapter = MemberAdapter(this, data.member)
        /*val secondRecyclerView = findViewById<RecyclerView>(R.id.second_list)
        secondRecyclerView.adapter = mAdapter*/

        findViewById<RecyclerView>(R.id.second_list).apply {
            layoutManager = LinearLayoutManager(this@SecondActivity, LinearLayoutManager.VERTICAL, false)
            adapter = MemberAdapter(data.member)
        }
    }


    fun getJsonDataFromRaw() : String {
        val jsonRaw = resources.openRawResource(R.raw.data2)
        var b: ByteArray
        b = jsonRaw.readBytes()
        val jsonString = String(b)
        return jsonString
    }
}