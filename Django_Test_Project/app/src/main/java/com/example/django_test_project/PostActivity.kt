package com.example.django_test_project

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostActivity : AppCompatActivity() {

    internal lateinit var retrofit: Retrofit
    internal lateinit var apiService: ApiService
    internal lateinit var comment: Call<Json_Test_Java>
    internal lateinit var comment2: Call<ResponseBody>

    internal lateinit var result:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        type1()

    }








    fun type1(){
        retrofit = Retrofit.Builder().baseUrl(ApiService.API_URL).addConverterFactory(GsonConverterFactory.create()).build()
        apiService = retrofit.create(ApiService::class.java)
        val version = Json_Test_Java("test5")

        comment = apiService.post_json_test_java("json", version)
        comment.enqueue(object : Callback<Json_Test_Java> {

            override fun onResponse(call: Call<Json_Test_Java>, response: Response<Json_Test_Java>) {
                Log.e("D_Test", "2차")
                if (response.isSuccessful) {
                    Log.e("post", "성공")
                } else {
                    val StatusCode = response.code()
                    Log.e("post", "Status Code : $StatusCode")
                }
            }
            override fun onFailure(call: Call<Json_Test_Java>, t: Throwable) {
                result = "error!!"
                Log.e("D_Test", "페일!")
            }
        })
    }




    fun get_code(){
        retrofit = Retrofit.Builder().baseUrl(ApiService.API_URL).build()
        apiService = retrofit.create(ApiService::class.java)
        comment2 = apiService.get_Test("json")
        comment2.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                try {

                    result = response.body()!!.string()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                result = "error!!"
                Log.e("D_Test", "페일!")
            }
        })
    }

}
