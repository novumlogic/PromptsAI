package com.priyasindkar.promptsai.ui.empty

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Loading() {
    Column(modifier = Modifier.fillMaxWidth().padding(end = 16.dp), horizontalAlignment = Alignment.End) {
        Surface(
            color = MaterialTheme.colorScheme.secondary,
            shape = RoundedCornerShape(20.dp, 4.dp, 20.dp, 20.dp),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(
                text = "...",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp),
            )
        }
    }
}