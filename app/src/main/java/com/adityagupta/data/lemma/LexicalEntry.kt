package com.adityagupta.data.lemma

data class LexicalEntry(
    val grammaticalFeatures: List<GrammaticalFeature>,
    val inflectionOf: List<InflectionOf>,
    val language: String,
    val lexicalCategory: LexicalCategory,
    val text: String
)