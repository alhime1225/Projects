package com.example.room_playground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.room_playground.dao.UserDao
import com.example.room_playground.data.AppDatabase
import com.example.room_playground.databinding.ActivityMainBinding
import com.example.room_playground.model.User
import com.example.room_playground.util.Coroutines

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Coroutines.main { insertToDB() }
    }

    private fun insertToDB(){
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "User"
        ).build()

        val user =  User(null, "Ai", "Test")
        db.userDao().insertAll(user)

//        Toast.makeText(applicationContext, "MESSAGE " + db.userDao().getAll(), Toast.LENGTH_LONG).show()
        Log.d("INSERTING_DB", db.userDao().getAll().toString())

    }

    fun insertToDB(view: View) {
        var firstName = view.findViewById<EditText>(R.id.editTextTextPersonName)
        var lastName = view.findViewById<EditText>(R.id.editTextTextPersonName2)
    }
}