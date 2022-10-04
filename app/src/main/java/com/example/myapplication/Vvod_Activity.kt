package com.example.myapplication
/*
var Name : String = nameText.text.toString()
var Surename : String = surenameText.text.toString()
var Number : String = numberText.text.toString()

*/



import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

var nums : MutableList<Numbers> = mutableListOf()
private lateinit var nameText: EditText
private lateinit var surenameText: EditText
private lateinit var numberText: EditText
private lateinit var btn : Button
private var index: Int = -1
class Vvod_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vvod)
        index = intent.getIntExtra("index", -1)



        nameText = findViewById(R.id.NameText)
        surenameText = findViewById((R.id.SurenameText))
        numberText = findViewById(R.id.NumbeerText)
        btn = findViewById(R.id.button3)

        if (index!=-1)
        {
            getContacts()
            btn.setText("Изменить")
            nameText.setText(nums[index].name)
            surenameText.setText(nums[index].surename)
            numberText.setText(nums[index].number)
        }
        btn.setOnClickListener{
            if (index==-1){
                getContacts()
                addNums(nameText.text.toString(),
                surenameText.text.toString(),
                numberText.text.toString())
            }
            else{
                nums[index].name= nameText.text.toString()
                nums[index].surename = surenameText.text.toString()
                nums[index].number = numberText.text.toString()
                val preferences = getSharedPreferences("pref", MODE_PRIVATE)
                preferences.edit {
                    this.putString("json", "")
                    this.putString("json", Gson().toJson(nums).toString())
                }
            }
            nameText.setText("")
            surenameText.setText("")
            numberText.setText("")
        }





    }
    private fun getContacts()
    {
        val preferences = getSharedPreferences("pref", MODE_PRIVATE)
        var json: String = ""
        if (!preferences.contains("json"))
        {
            return
        }
        else
        {
            json = preferences.getString("json", "notJSON").toString()
        }
        val numsList = Gson().fromJson<List<Numbers>>(json,object:TypeToken<List<Numbers>>(){}.type)
        nums.clear()
        nums.addAll(numsList)
    }

    private fun addNums(name:String, surename:String, number:String)
    {
        val Temp = Numbers(name,surename,number)
        nums.add(Temp)
        val preferences = getSharedPreferences("pref", MODE_PRIVATE)
        preferences.edit {
            this.putString("json", Gson().toJson(nums).toString())
        }
    }

}