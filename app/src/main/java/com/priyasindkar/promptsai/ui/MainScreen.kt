package com.priyasindkar.promptsai.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.priyasindkar.promptsai.R
import com.priyasindkar.promptsai.ui.empty.EmptyPrompt
import com.priyasindkar.promptsai.ui.empty.Loading
import com.priyasindkar.promptsai.ui.messages.Messages
import com.priyasindkar.promptsai.ui.messages.UserInput
import com.priyasindkar.promptsai.ui.theme.CustomAppBarTitleTypography
import com.priyasindkar.promptsai.ui.theme.PromptsAITheme
import com.priyasindkar.promptsai.viewmodel.MessagesViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    messagesViewModel: MessagesViewModel = viewModel()
) {
    val scrollState = rememberLazyListState()
    val topBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topBarState)
    val scope = rememberCoroutineScope()

    val uiState by messagesViewModel.uiState.collectAsState()

    PromptsAITheme {
        Box {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .nestedScroll(scrollBehavior.nestedScrollConnection)
                    ) {
                        if (uiState.messages.isEmpty()) {
                            EmptyPrompt(
                                modifier = Modifier
                                    .weight(1f)
                            ) {
                                messagesViewModel.updateUserInputVisibility(true)
                            }
                        } else {
                            Messages(
                                messages = uiState.messages,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(16.dp),
                                scrollState = scrollState
                            )
                        }

                        if (uiState.isLoading) {
                            Loading()
                        }

                        AnimatedVisibility(
                            visible = uiState.showUserInput,
                            enter = slideInVertically(initialOffsetY = { it / 2 }),
                        ) {
                            UserInput(
                                selectedPrompt = uiState.selectedPrompt,
                                onMessageSent = { content ->
                                    messagesViewModel.fetchAnswers(content)
                                },
                                onPromptSelected = { promptType ->
                                    messagesViewModel.updatePromptType(promptType)
                                },
                                onResetScroll = {
                                    scope.launch {
                                        scrollState.scrollToItem(0)
                                    }
                                },
                                // Use navigationBarsPadding() imePadding() and , to move the input panel above both the
                                // navigation bar, and on-screen keyboard (IME)
                                modifier = Modifier
                                    .navigationBarsPadding()
                                    .imePadding(),
                            )
                        }
                    }
                }
            }

            PromptsAIAppBar(
                onNavIconPressed = {},
                title = {
                    ToolbarTitleText()
                }
            )
        }
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
private fun ToolbarTitleText() {
    val gradientColors = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.inversePrimary,
        MaterialTheme.colorScheme.secondary
    )

    Text(
        stringResource(id = R.string.app_name).uppercase(),
        style = CustomAppBarTitleTypography.copy(
            brush = Brush.linearGradient(
                colors = gradientColors
            )
        )
    )
}