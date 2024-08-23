package com.example.retrofitapi

import android.content.Context
import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity


import com.example.retrofitapi.databinding.ActivityAddUserBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddUser : AppCompatActivity() {
    private lateinit var binding: ActivityAddUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)

        binding.imageButton.setOnClickListener{
            saveUser(this)
        }
        setContentView(binding.root)





//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }


    }



    private fun saveUser(context: Context){
        val title = binding.editTextTitle.toString()
        val body = binding.editTextDesc.toString()
        val user =UserData(0,title,body,1)
        val call= ApiUtilities.getPlacesApi().addData(user)
        call.enqueue(object : Callback<UserData?> {
            override fun onResponse(call: Call<UserData?>, response: Response<UserData?>) {
                finish()
                Toast.makeText(context,"User Added", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<UserData?>, t: Throwable) {
                Toast.makeText(context,"unsuccessfully",Toast.LENGTH_SHORT).show()
            }
        })




    }
    
    
    
    
}


