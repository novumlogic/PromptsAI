package com.priyasindkar.promptsai.utils

import java.text.SimpleDateFormat

val formatter = SimpleDateFormat("MM/dd/yyyy")

fun getCurrentTimestamp() : String {
    return formatter.format(System.currentTimeMillis())
}