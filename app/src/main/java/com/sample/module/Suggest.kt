package com.sample.module

data class Suggest(
    val note: String? = "",
    val issue: List<Issue> = listOf(),
    val title: String? = ""
)

data class Issue(
    val title: String? = "",
    val descriptionFilterHtml: String? = "",
    val startDate: String? = "",
    val endDate: String? = ""
)