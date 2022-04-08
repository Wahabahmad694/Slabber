package com.example.slabber.`interface`

import com.example.slabber.models.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.concurrent.TimeUnit
import com.example.slabber.models.Thread
import retrofit2.http.GET

interface ApiServices {
    @POST("auth/signup")
    suspend fun signup(@Body user: User): User

    @GET("auth/login/{id}")
    suspend fun login(@Path("id") userId: String): User

    @GET("user")
    suspend fun allUsers(): List<User>

    @GET("chat/{userId}")
    suspend fun allThreads(@Path("userId") userId: String): List<Thread>

    @GET("thread/{threadId}")
    suspend fun thread(@Path("threadId ") threadId: String): Thread

    companion object {
        private var apiServices: ApiServices? = null
        fun getInstance(): ApiServices {
            if (apiServices == null) {

                val interceptor = HttpLoggingInterceptor()

                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES).build()

                apiServices = Retrofit.Builder()
                    .baseUrl("http://192.168.2.216:3000/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create()).client(client)
                    .build().create(ApiServices::class.java)

            }
            return apiServices!!
        }
    }
}