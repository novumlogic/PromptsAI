package com.priyasindkar.promptsai.ui.messages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.priyasindkar.promptsai.model.Message
import com.priyasindkar.promptsai.ui.theme.*

val ChatBubbleShapeForUser = RoundedCornerShape(4.dp, 20.dp, 20.dp, 20.dp)
val ChatBubbleShapeForAI = RoundedCornerShape(20.dp, 4.dp, 20.dp, 20.dp)

@Composable
fun Messages(
    messages: List<Message>,
    scrollState: LazyListState,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        LazyColumn(
            reverseLayout = true,
            state = scrollState,
            contentPadding =
            WindowInsets.statusBars.add(WindowInsets(top = 90.dp)).asPaddingValues(),
            modifier = Modifier
                .fillMaxSize()
        ) {
            for (index in messages.indices) {
                item {
                    Message(
                        msg = messages[index],
                    )
                }
            }
        }
    }
}

@Composable
fun Message(msg: Message) {
    val spaceBetweenAuthors = Modifier.padding(top = 8.dp)
    Row(modifier = spaceBetweenAuthors) {
        TextMessage(
            msg = msg,
            modifier = Modifier
                .padding(end = 16.dp)
                .weight(1f)
        )
    }
}

@Composable
fun TextMessage(
    msg: Message,
    modifier: Modifier = Modifier
) {
    val arrangement = if (msg.isQuestion) Alignment.Start else Alignment.End
    Column(modifier = modifier, horizontalAlignment = arrangement) {
        Timestamp(msg)
        ChatItemBubble(msg)
        // Between bubbles
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
private fun Timestamp(msg: Message) {
    Row(
        modifier = Modifier.semantics(mergeDescendants = true) {},
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = msg.timestamp,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.alignBy(LastBaseline),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun ChatItemBubble(message: Message) {
    Column {
        Surface(
            color = if (message.isQuestion) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
            shape = if (message.isQuestion) ChatBubbleShapeForUser else ChatBubbleShapeForAI
        ) {
            ClickableMessage(
                message = message,
            )
        }
    }
}

@Composable
fun ClickableMessage(
    message: Message,
) {
    val customTextSelectionColors = TextSelectionColors(
        handleColor = Color.White,
        backgroundColor = Color.White.copy(alpha = 0.4f)
    )

    val font = if (message.isQuestion) MontserratItalicsFont else MontserratRegularFont

    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
        SelectionContainer() {
            ClickableText(
                text = AnnotatedString(message.content),
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = LocalContentColor.current,
                    fontFamily = font
                ),
                modifier = Modifier.padding(16.dp),
                onClick = {
                }
            )
        }
    }
}