package com.priyasindkar.promptsai.ui.empty

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.priyasindkar.promptsai.R
import com.priyasindkar.promptsai.ui.theme.EmptyTextTypography

@OptIn(ExperimentalTextApi::class)
@Composable
fun EmptyPrompt(modifier: Modifier, onStartClicked: () -> Unit) {
    Column(
        modifier = modifier
            .wrapContentSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val gradientColors = listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.inversePrimary,
//            MaterialTheme.colorScheme.secondary,
        )

        Text(
            text = stringResource(id = R.string.empty_prompt_title),
            style = EmptyTextTypography.copy(
                brush = Brush.linearGradient(
                    colors = gradientColors
                ),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.size(8.dp))

        Text(
            text = stringResource(id = R.string.empty_prompt),
            style = EmptyTextTypography.copy(
                color = MaterialTheme.colorScheme.primary
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.size(16.dp))

        Button(onClick = onStartClicked) {
            Text(
                "Start Sending Requests",
                modifier = Modifier.padding(horizontal = 32.dp),
                style = EmptyTextTypography,
            )
        }
    }
}