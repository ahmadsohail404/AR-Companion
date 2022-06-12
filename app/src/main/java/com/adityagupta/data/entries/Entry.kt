package com.adityagupta.data.entries

data class Entry(
    val etymologies: List<String>,
    val grammaticalFeatures: List<GrammaticalFeature>,
    val homographNumber: String,
    val pronunciations: List<Pronunciation>,
    val senses: List<Sense>
)