package com.priyasindkar.promptsai.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Priya Sindkar.
 */
class APIConfig {
    companion object {
        val retrofit = Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .client(OkHttpClient().newBuilder()
                        .connectTimeout(60000, TimeUnit.MILLISECONDS)
                        .readTimeout(60000, TimeUnit.MILLISECONDS).build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun getRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }

        return Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .client(OkHttpClient().newBuilder()
                    .addInterceptor(interceptor)
                        .connectTimeout(60000, TimeUnit.MILLISECONDS)
                        .readTimeout(60000, TimeUnit.MILLISECONDS).build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}