package com.priyasindkar.promptsai.retrofit

import com.priyasindkar.promptsai.model.OpenAIRequestBody
import com.priyasindkar.promptsai.model.OpenAIResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("completions")
    suspend fun fetchAnswer(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("Authorization") apiKey: String,
        @Body request: OpenAIRequestBody
    ): Response<OpenAIResponseBody>
}