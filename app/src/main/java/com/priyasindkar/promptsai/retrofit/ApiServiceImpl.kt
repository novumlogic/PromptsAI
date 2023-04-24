package com.priyasindkar.promptsai.retrofit

import com.priyasindkar.promptsai.model.OpenAIRequestBody
import com.priyasindkar.promptsai.model.OpenAIResponseBody
import retrofit2.Response

class ApiServiceImpl : ApiService {
    override suspend fun fetchAnswer(
        contentType: String,
        apiKey: String,
        request: OpenAIRequestBody
    ): Response<OpenAIResponseBody> {
        return APIConfig.retrofit.create(ApiService::class.java)
            .fetchAnswer(apiKey = apiKey, request = request)
    }
}