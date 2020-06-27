package com.sample.module

import com.google.gson.annotations.SerializedName

data class Suggest(
    val note: String? = null,
    @SerializedName("issue") val issues: List<Issue> = listOf(),
    val title: String? = null
)

data class Issue(
    val title: String? = "",
    val descriptionFilterHtml: String? = "",
    val startDate: String? = "",
    val endDate: String? = ""
)