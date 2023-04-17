package com.priyasindkar.promptsai.ui.messages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserInput(
    selectedPrompt: PromptTypes,
    onMessageSent: (String) -> Unit,
    onPromptSelected: (PromptTypes) -> Unit,
    modifier: Modifier = Modifier,
    onResetScroll: () -> Unit = {}
) {
    var textState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }

    // Used to decide if the keyboard should be shown
    var textFieldFocusState by remember { mutableStateOf(false) }

    Surface(tonalElevation = 2.dp) {
        Column(modifier = modifier) {
            UserInputText(
                selectedPrompt = selectedPrompt,
                textFieldValue = textState,
                onTextChanged = { textState = it },
                keyboardShown = textFieldFocusState,
                onTextFieldFocused = { focused ->
                    if (focused) onResetScroll.invoke()

                    textFieldFocusState = focused
                },
                focusState = textFieldFocusState,
                onMessageSent = onMessageSent,
                resetTextField = {
                    textState = TextFieldValue()
                }
            )

            PromptInputs(onChipSelected = onPromptSelected)
        }
    }
}

@Composable
fun SendButton(modifier: Modifier, onMessageSent: () -> Unit) {
    val disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)

    val buttonColors = ButtonDefaults.buttonColors(
        disabledContainerColor = Color.Transparent,
        disabledContentColor = disabledContentColor
    )

    // Send button
    Column(
        modifier = modifier
            .padding(top = 8.dp, bottom = 8.dp, end = 8.dp)
    ) {
        Button(
            onClick = onMessageSent,
            colors = buttonColors,
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(Icons.Filled.Send, contentDescription = "Send Button")
        }
    }

}

val KeyboardShownKey = SemanticsPropertyKey<Boolean>("KeyboardShownKey")
var SemanticsPropertyReceiver.keyboardShownProperty by KeyboardShownKey

@ExperimentalFoundationApi
@Composable
fun UserInputText(
    selectedPrompt: PromptTypes,
    keyboardType: KeyboardType = KeyboardType.Text,
    onTextChanged: (TextFieldValue) -> Unit,
    textFieldValue: TextFieldValue,
    keyboardShown: Boolean,
    onTextFieldFocused: (Boolean) -> Unit,
    focusState: Boolean,
    onMessageSent: (String) -> Unit,
    resetTextField: () -> Unit
) {
    var textState = textFieldValue

    Row(
        modifier = Modifier
            .fillMaxWidth()
//            .height(64.dp)
            .semantics {
                contentDescription = "question"
                keyboardShownProperty = keyboardShown
            },
        horizontalArrangement = Arrangement.End
    ) {
        Surface {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
//                    .height(64.dp)
                        .weight(0.8f)
                        .align(Alignment.Bottom)
                ) {
                    var lastFocusState by remember { mutableStateOf(false) }

                    TextField(
                        value = textFieldValue,
                        onValueChange = {
                            onTextChanged(it)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
//                        .height(80.dp)
                            .padding(start = 8.dp, bottom = 8.dp)
                            .align(Alignment.CenterStart)
                            .onFocusChanged { state ->
                                if (lastFocusState != state.isFocused) {
                                    onTextFieldFocused(state.isFocused)
                                }
                                lastFocusState = state.isFocused
                            },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = keyboardType,
                            imeAction = ImeAction.Send
                        ),
                        singleLine = true,
                        maxLines = 1,
//                        cursorBrush = SolidColor(LocalContentColor.current),
                        textStyle = LocalTextStyle.current.copy(color = LocalContentColor.current)
                    )

                    val disableContentColor =
                        MaterialTheme.colorScheme.onSurfaceVariant
                    if (textFieldValue.text.isEmpty() && !focusState) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 16.dp),
                            text = "Message",
                            style = MaterialTheme.typography.bodyLarge.copy(color = disableContentColor)
                        )
                    }
                }

                SendButton(
                    modifier = Modifier
                        .weight(0.2f)
                        .wrapContentSize(),
                    onMessageSent = {
                        if (textState.text.isEmpty()) return@SendButton

                        onMessageSent.invoke(textState.text)
                        // Reset text field and close keyboard
//                        textState = TextFieldValue()
                        resetTextField.invoke()
                    }
                )
            }

        }
    }
}