package com.sohail.data.entries

data class Subsense(
    val definitions: List<String>,
    val domainClasses: List<DomainClasse>,
    val domains: List<Domain>,
    val examples: List<ExampleX>,
    val id: String,
    val notes: List<Note>,
    val registers: List<RegisterX>,
    val semanticClasses: List<SemanticClasse>,
    val shortDefinitions: List<String>
)