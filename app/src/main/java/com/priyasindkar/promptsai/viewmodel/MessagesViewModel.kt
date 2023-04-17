package com.priyasindkar.promptsai.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priyasindkar.promptsai.data.MessagesRepository
import com.priyasindkar.promptsai.data.MessagesUiState
import com.priyasindkar.promptsai.retrofit.ApiServiceImpl
import com.priyasindkar.promptsai.ui.messages.PromptTypes
import com.priyasindkar.promptsai.model.Message
import com.priyasindkar.promptsai.model.OpenAIRequestBody
import com.priyasindkar.promptsai.utils.getCurrentTimestamp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MessagesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MessagesUiState(emptyList()))
    val uiState: StateFlow<MessagesUiState> = _uiState.asStateFlow()

    private val messagesRepository = MessagesRepository(ApiServiceImpl())

    fun fetchAnswers(prompt: String) {
        if (uiState.value.selectedPrompt == PromptTypes.NONE) return
        _uiState.update { currentState ->
            currentState.copy(
                isLoading = true
            )
        }

        val promptRequestText = "${uiState.value.selectedPrompt.promptText} - $prompt"
        uiState.value.addMessage(Message(promptRequestText, getCurrentTimestamp(), true))
        val openAIRequestBody = OpenAIRequestBody(prompt = promptRequestText)

        viewModelScope.launch {
            val openAIResponseBody = messagesRepository.fetchAnswer(openAIRequestBody)

            _uiState.update { currentState ->
                currentState.copy(
                    initialMessages = currentState.messages,
                    isLoading = false
                )
            }

            if (openAIResponseBody.isSuccessful) {
                openAIResponseBody.body()?.choices?.firstOrNull()?.let {
                    _uiState.value.addMessage(Message(it.text.trim(), getCurrentTimestamp(), false))
                } ?: run {
//                    uiState.value.error = mutableStateOf(openAIResponseBody.errorBody().toString())
//                    _uiState.update {currentState ->
//                        currentState.copy(
//                            error = openAIResponseBody.errorBody().toString()
//                        )
//                    }
                    _uiState.value.addMessage(
                        Message(
                            "Failed: ${
                                openAIResponseBody.errorBody().toString()
                            }", getCurrentTimestamp(), false
                        )
                    )
                }
            } else {
//                _uiState.update {currentState ->
//                    currentState.copy(
//                        error = openAIResponseBody.message()
//                    )
//                }
                _uiState.value.addMessage(
                    Message(
                        "Failed: ${openAIResponseBody.message()}",
                        getCurrentTimestamp(),
                        false
                    )
                )
            }
            _uiState.update { currentState ->
                currentState.copy(
                    initialMessages = currentState.messages,
                    isLoading = false
                )
            }
        }
    }

    fun updatePromptType(promptType: PromptTypes) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedPrompt = promptType
            )
        }
    }

    fun updateUserInputVisibility(isVisible: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                showUserInput = isVisible
            )
        }
    }
}

val mockQuestions = listOf<Message>(
    Message("Rewritten text with a positive tone could be:\n" +
            "\n" +
            "Oh, I see! I had misunderstood your earlier message. Just to confirm, do you mean that I should focus on the design, or is there something else you would like me to work on as well?",  getCurrentTimestamp(),false),

    Message("Rewrite the text with a positive tone in the text - Oh my god! I thought you meant I was supposed to work on  the design only", getCurrentTimestamp(),
        true),
    Message("Rewritten text with a positive tone could be:\n" +
            "\n" +
            "Oh, I see! I had misunderstood your earlier message. Just to confirm, do you mean that I should focus on the design, or is there something else you would like me to work on as well?",  getCurrentTimestamp(),false),

    Message("Rewrite the text with a positive tone in the text - Oh my god! I thought you meant I was supposed to work on  the design only", getCurrentTimestamp(),
        true),
    Message("Rewritten text with a positive tone could be:\n" +
            "\n" +
            "Oh, I see! I had misunderstood your earlier message. Just to confirm, do you mean that I should focus on the design, or is there something else you would like me to work on as well?",  getCurrentTimestamp(),false),

    Message("Rewrite the text with a positive tone in the text - Oh my god! I thought you meant I was supposed to work on  the design only", getCurrentTimestamp(),
        true),
    Message("Rewritten text with a positive tone could be:\n" +
            "\n" +
            "Oh, I see! I had misunderstood your earlier message. Just to confirm, do you mean that I should focus on the design, or is there something else you would like me to work on as well?",  getCurrentTimestamp(),false),

    Message("Rewrite the text with a positive tone in the text - Oh my god! I thought you meant I was supposed to work on  the design only", getCurrentTimestamp(),
        true),
    Message("Rewritten text with a positive tone could be:\n" +
            "\n" +
            "Oh, I see! I had misunderstood your earlier message. Just to confirm, do you mean that I should focus on the design, or is there something else you would like me to work on as well?",  getCurrentTimestamp(),false),

    Message("Rewrite the text with a positive tone in the text - Oh my god! I thought you meant I was supposed to work on  the design only", getCurrentTimestamp(),
        true),
)