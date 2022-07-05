package com.sohail.data.entries

data class Definition(
    val id: String,
    val metadata: Metadata,
    val results: List<Result>,
    val word: String
)