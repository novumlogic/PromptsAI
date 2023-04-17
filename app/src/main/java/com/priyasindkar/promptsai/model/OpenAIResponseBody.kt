package com.priyasindkar.promptsai.model

data class OpenAIResponseBody(
    val choices: List<Choice>,
    val created: Int,
    val id: String,
    val model: String,
    val `object`: String,
    val usage: Usage
)