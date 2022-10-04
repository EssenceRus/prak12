package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Vivod_Activity : AppCompatActivity() {
    private val Nums : MutableList<Numbers> = mutableListOf()
    private lateinit var rv: RecyclerView
    private  var index = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vivod)

        val adapter = NumbersRVAdapter(this, Nums)
        getNums()
        val rvListener = object : NumbersRVAdapter.ItemClickListener{
            override fun onItemClick(view: View?, position: Int) {
                val intent= Intent(this@Vivod_Activity, Vvod_Activity::class.java)
                intent.putExtra("index", position)
                index = position
                startActivity(intent)
            }
        }
        adapter.setOnClickListener(rvListener)
        rv = findViewById(R.id.recycleView)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)


    }
    private  fun getNums()
    {
        val preferences = getSharedPreferences("pref", MODE_PRIVATE)
        var json : String = ""
        if (!preferences.contains(("json"))){
            return
        }
        else
        {
            json = preferences.getString("json", "notJSON").toString()
        }
        val NumsList = Gson().fromJson<List<Numbers>>(json, object:TypeToken<List<Numbers>>(){}.type)
        Nums.addAll(NumsList)
    }

    override fun onResume() {
        super.onResume()

        if (index != -1) {
            Nums.clear()
            getNums()
            rv.adapter?.notifyItemChanged(index)

        }
    }
}
