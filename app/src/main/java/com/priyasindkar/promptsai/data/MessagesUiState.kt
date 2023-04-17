package com.priyasindkar.promptsai.data

import androidx.compose.runtime.toMutableStateList
import com.priyasindkar.promptsai.ui.messages.PromptTypes
import com.priyasindkar.promptsai.model.Message

data class MessagesUiState(
    val initialMessages: List<Message>,
    val error: String = "",
    val selectedPrompt: PromptTypes = PromptTypes.NONE,
    val isLoading: Boolean = false,
    val showUserInput: Boolean = initialMessages.isNotEmpty()
) {
    private val _messages: MutableList<Message> = initialMessages.toMutableStateList()
    val messages: List<Message> = _messages

    fun addMessage(msg: Message) {
        _messages.add(0, msg) // Add to the beginning of the list
    }
}