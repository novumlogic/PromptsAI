package com.priyasindkar.promptsai.ui.messages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PromptInputs(onChipSelected: (PromptTypes) -> Unit) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var selected by remember { mutableStateOf(PromptTypes.NONE) }

//        Divider(
//            thickness = 2.dp,
//            modifier = Modifier.padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
//            color = MaterialTheme.colorScheme.inversePrimary
//        )

        FlowRow(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            PromptTypes.values().filterNot { it == PromptTypes.NONE }.forEach {
                PromptChip(it == selected, labelName = it.promptLabel) {
                    selected = if (it == selected) PromptTypes.NONE else it

                    onChipSelected.invoke(selected)
                }
            }
        }

        if (selected != PromptTypes.NONE) PromptText(selected)
    }
}

@Composable
fun PromptChip(selected: Boolean, labelName: String, onChipClicked: () -> Unit) {
    val colors = if (selected) {
        SuggestionChipDefaults.suggestionChipColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            labelColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContainerColor = Color.Transparent,
            disabledLabelColor = Color.Gray
        )
    } else {
        SuggestionChipDefaults.suggestionChipColors()
    }
    SuggestionChip(
        colors = colors,
        border = if (selected) null else SuggestionChipDefaults.suggestionChipBorder(),
        label = { Text(text = labelName, style = MaterialTheme.typography.bodySmall) },
        onClick = onChipClicked,
    )
    Spacer(modifier = Modifier.width(width = 16.dp))
}

@Composable
fun PromptText(promptTypes: PromptTypes) {
    Text(
        text = promptTypes.promptText,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

enum class PromptTypes(val promptText: String, val promptLabel: String) {
    NONE("None", "None"),
    ALTERNATIVE("Rewrite the text with some formal words", "Alternate Text"),
    POSITIVE_TONE("Rewrite the text with positive tone in the text", "Positive Tone"),
    SYNONYMS("Suggest synonyms for the word", "Synonym"),
    TRANSLATE("Translate the text in Spanish", "Translate (Spanish)")
}