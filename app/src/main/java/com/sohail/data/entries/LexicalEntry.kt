package com.sohail.data.entries

data class LexicalEntry(
    val entries: List<Entry>,
    val language: String,
    val lexicalCategory: LexicalCategory,
    val phrases: List<Phrase>,
    val text: String
)