package com.example.retrofitapi

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.retrofitapi.databinding.ActivityUpdateUserBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class updateUser : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateUserBinding


    private lateinit var user :UserData




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getSerializableExtra("user") as? UserData
        if (user != null) {
            binding.editTextTitle.setText(user.title)
            binding.editTextDesc.setText(user.body)



        }else{
            finish()
            return
        }

        binding.imageButton.setOnClickListener{
            val title = binding.editTextTitle.text.toString()
            val body = binding.editTextDesc.text.toString()
            val userData =UserData(user.id,title,body,1)

            updateUserData(user,this)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    private fun updateUserData(user:UserData,context: Context){
        val call= ApiUtilities.getPlacesApi().updateData(user.id,user)
        call.enqueue(object : Callback<UserData?> {
            override fun onResponse(call: Call<UserData?>, response: Response<UserData?>) {
                finish()
                Toast.makeText(context,"User Updates", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<UserData?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
            }
        })
    }
}