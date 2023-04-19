package com.example.recyclerview_basic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class ThirdActivity : AppCompatActivity() {

    val jsonString by lazy { getJsonDataFromRaw()}
    private lateinit var adapter: MemberAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        var thirdRecyclerView = this.findViewById<RecyclerView>(R.id.third_list)

        var listData  = mutableListOf<Item>()

        listData.add(Item(lastStatus = 2))
        listData.add(Item(lastStatus = 1))

        /*
        this.findViewById<RecyclerView>(R.id.third_list).apply{
            layoutManager = LinearLayoutManager(this@ThirdActivity, LinearLayoutManager.VERTICAL, false)
            adapter = MemberAdapter(listData)
        }
        */
        adapter = MemberAdapter(listData)
        thirdRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        thirdRecyclerView.adapter = adapter



        val firstBtn = this.findViewById<Button>(R.id.first_btn)
        firstBtn.setOnClickListener{
            val intent = Intent(this@ThirdActivity, MainActivity::class.java)
            startActivity(intent)
        }
        val secondBtn = this.findViewById<Button>(R.id.second_btn)
        secondBtn.setOnClickListener{
            val intent = Intent(this@ThirdActivity, SecondActivity::class.java)
            startActivity(intent)
        }
        val thirdBtn = this.findViewById<Button>(R.id.third_btn)
        thirdBtn.setOnClickListener{
            val intent = Intent(this@ThirdActivity, ThirdActivity::class.java)
            startActivity(intent)
        }

        val dataBtn = this.findViewById<Button>(R.id.data_btn)
        dataBtn.setOnClickListener {
            val data = Gson().fromJson(jsonString, Test::class.java)
            data.member.add(
                Item(lastStatus = 1)
            )
            val mAdapter = MemberAdapter(data.member)
            thirdRecyclerView.adapter = mAdapter
        }

        val dataBtn2 = this.findViewById<Button>(R.id.data_btn2)
        dataBtn2.setOnClickListener {
            val data = Gson().fromJson(jsonString, Test::class.java)
            data.member.add(
                Item(lastStatus = 1)
            )
            adapter.setData(data.member)
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
