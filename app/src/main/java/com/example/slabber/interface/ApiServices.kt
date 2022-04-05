package com.example.slabber.`interface`

import com.example.slabber.models.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ApiServices {
    @GET("movielist.json")
    suspend fun getChat(): List<User>

    companion object {
        private var apiServices: ApiServices? = null
        fun getInstance(): ApiServices {
            if (apiServices == null) {

                val interceptor =  HttpLoggingInterceptor()

                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES).build()
                apiServices = Retrofit.Builder()
                    .baseUrl("https://howtodoandroid.com/apis/")
                    .addConverterFactory(GsonConverterFactory.create()).client(client)
                    .build().create(ApiServices::class.java)

            }
            return apiServices!!
        }
    }
}