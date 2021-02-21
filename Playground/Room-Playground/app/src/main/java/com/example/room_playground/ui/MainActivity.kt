package com.example.room_playground.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.room.Room
import com.example.room_playground.R
import com.example.room_playground.data.AppDatabase
import com.example.room_playground.databinding.ActivityMainBinding
import com.example.room_playground.model.User
import com.example.room_playground.util.Coroutines

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var listItem : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button2 : Button = findViewById(R.id.button2)
        button2.setOnClickListener {

            var first : TextView = findViewById(R.id.textView)
            var last : TextView = findViewById(R.id.textView2)

            first.setText(listItem.firstName)
            last.setText(listItem.lastName)
        }

        var button : Button = findViewById(R.id.button)
        button.setOnClickListener {
            Coroutines.main { insertToDB() }
        }

    }

    private fun insertToDB(){
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "User"
        ).build()

        var firstEditText : EditText = findViewById(R.id.editTextTextPersonName)
        var lastEditText : EditText = findViewById(R.id.editTextTextPersonName2)

        val user =  User(null, firstEditText.text.toString(), lastEditText.text.toString())
        db.userDao().insertAll(user)
        var userObject = db.userDao().getAll()
        Log.d("INSERTING_DB", userObject.toString())
        listItem = userObject.get(userObject.size-1)

//        first.setText(firstEditText.text)
//        last.setText(lastEditText.text)

//        Toast.makeText(applicationContext, "MESSAGE " + db.userDao().getAll(), Toast.LENGTH_LONG).show()
//        Log.d("INSERTING_DB", db.userDao().getAll().toString())

    }


}