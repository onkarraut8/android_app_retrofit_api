package com.example.retrofitapi

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserAdaptor (val context: Context, private var userData:List<UserData>):RecyclerView.Adapter<UserAdaptor.ViewHolder>(){

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
         var title : TextView = itemView.findViewById(R.id.textView_title)
        var row: View =itemView.findViewById(R.id.row_user)
       //var id : TextView = itemView.findViewById(R.id.textView_Id)
         var disc : TextView = itemView.findViewById(R.id.textView_Disc)
         //var body : TextView = itemView.findViewById(R.id.textView_Body)
        val delete :ImageButton =itemView.findViewById(R.id.imageButton_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_user, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userData[position]
        holder.title.text = user.title
       // holder.id.text = user.id.toString()
        holder.disc.text =user.body
       // holder.body.text =user.body

        holder.row.setOnClickListener{
            val intent = Intent(holder.itemView.context,updateUser::class.java).apply {
              putExtra("user",user)
            }
            holder.itemView.context.startActivity(intent)

        }

        holder.delete.setOnClickListener{
            deleteUser(user.id,context)

        }



    }

   private fun deleteUser(id:Int, context: Context){
       val call= ApiUtilities.getPlacesApi().deleteData(id)
       call.enqueue(object : Callback<UserData?> {
           override fun onResponse(call: Call<UserData?>, response: Response<UserData?>) {
               refreshData()
               Toast.makeText(context,"User Deleted", Toast.LENGTH_SHORT).show()
           }

           override fun onFailure(call: Call<UserData?>, t: Throwable) {
               Log.d("MainActivity", "onFailure: " + t.message)
           }
       })
   }

    fun refreshData(user :List<UserData>){
        userData = user
        notifyDataSetChanged()
    }

    private fun refreshData(){

        val call= ApiUtilities.getPlacesApi().getData()
        call.enqueue(object : Callback<List<UserData>?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<List<UserData>?>,
                response: Response<List<UserData>?>
            ) {
                val responseBody =response.body()!!
                userData = responseBody
                notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<UserData>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
            }
        })



    }
}