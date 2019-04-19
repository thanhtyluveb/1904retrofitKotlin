package com.example.recyclerview.remote.retrofit

import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private var retrofit: Retrofit? = null

    fun getClient(baseUrl: String): Retrofit? {

        var buider = OkHttpClient().newBuilder().addInterceptor { chain ->
            val originalRequest = chain.request()

            val builder = originalRequest.newBuilder().header(
                "Authorization",
                Credentials.basic("aUsername", "aPassword")
            )

            val newRequest = builder.build()
            chain.proceed(newRequest)

        }.readTimeout(5000, TimeUnit.MILLISECONDS)
            .writeTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
            .build()




        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .client(buider)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

}