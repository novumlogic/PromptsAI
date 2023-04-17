package com.priyasindkar.promptsai.model

import com.priyasindkar.promptsai.retrofit.ApiConstants

data class OpenAIRequestBody(
    var model: String = ApiConstants.OPEN_AI_MODEL,
    val prompt: String,
    val max_tokens: Int = 150,
    val temperature: Float = 0f
)