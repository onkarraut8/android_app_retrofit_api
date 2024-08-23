package com.example.retrofitapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.content.Context
import android.content.Intent
import android.util.Log
import android.util.Log.d
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
private const val BASE_URL = "http://loZcalhost:8085/api/category"
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var useradapter :UserAdaptor



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =ActivityMainBinding.inflate(layoutInflater)



        binding.recyclerviewUserView.setHasFixedSize(true)
        binding.recyclerviewUserView.layoutManager = LinearLayoutManager(this)

        getUserData()


        binding.floatingActionButtonAddUser.setOnClickListener{
            val intent=Intent(this@MainActivity,AddUser::class.java)
            startActivity(intent)
        }


//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        setContentView(binding.root)
    }

    private fun getUserData(){

        val call= ApiUtilities.getPlacesApi().getData()
        call.enqueue(object : Callback<List<UserData>?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<List<UserData>?>,
                response: Response<List<UserData>?>
            ) {
                val responseBody =response.body()!!
                useradapter = UserAdaptor(baseContext!!, responseBody)
                useradapter.notifyDataSetChanged()
                binding.recyclerviewUserView.adapter = useradapter
            }

            override fun onFailure(call: Call<List<UserData>?>, t: Throwable) {
                d("MainActivity", "onFailure: "+t.message)
            }
        })



    }
}