package com.priyasindkar.promptsai.retrofit

import com.priyasindkar.promptsai.model.OpenAIRequestBody
import com.priyasindkar.promptsai.model.OpenAIResponseBody
import retrofit2.Response

class ApiServiceImpl : ApiService {

    private val apiConfig by lazy { APIConfig() }

    override suspend fun fetchAnswer(
        contentType: String,
        apiKey: String,
        request: OpenAIRequestBody
    ): Response<OpenAIResponseBody> {
        return APIConfig().getRetrofit().create(ApiService::class.java)
            .fetchAnswer(apiKey = apiKey, request = request)
    }
}