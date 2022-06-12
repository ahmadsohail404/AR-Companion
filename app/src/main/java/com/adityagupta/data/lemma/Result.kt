package com.adityagupta.data.lemma

data class Result(
    val id: String,
    val language: String,
    val lexicalEntries: List<LexicalEntry>,
    val word: String
)