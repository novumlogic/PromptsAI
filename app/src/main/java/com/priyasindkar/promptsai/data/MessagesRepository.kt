package com.priyasindkar.promptsai.data

import com.priyasindkar.promptsai.retrofit.ApiConstants
import com.priyasindkar.promptsai.retrofit.ApiService
import com.priyasindkar.promptsai.model.OpenAIRequestBody
import com.priyasindkar.promptsai.model.OpenAIResponseBody
import retrofit2.Response

class MessagesRepository(private val apiService: ApiService) {

    suspend fun fetchAnswer(openAIRequestBody: OpenAIRequestBody): Response<OpenAIResponseBody> {
        return apiService.fetchAnswer(
            apiKey = ApiConstants.HEADER_AUTHORIZATION,
            request = openAIRequestBody
        )
    }
}