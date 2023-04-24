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

        private val interceptor = HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(
                OkHttpClient().newBuilder()
                    .addInterceptor(interceptor)
                    .connectTimeout(60000, TimeUnit.MILLISECONDS)
                    .readTimeout(60000, TimeUnit.MILLISECONDS).build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}