package com.sample.module

import com.google.gson.annotations.SerializedName

data class Event(
    val file: List<File>? = listOf(),
    val link: List<Link>? = listOf(),
    val id: String? = "",
    @SerializedName("istop") val isTop: String? = "",
    val img: List<EventImage>? = listOf(),
    val subject: String? = "",
    @SerializedName("joinunit") val joinUnit: String? = "",
    @SerializedName("liaisonemail") val liaisonEmail: String? = "",
    @SerializedName("datasourceunit") val dataSourceUnit: String? = "",
    @SerializedName("activityplace") val activityPlace: String? = "",
    @SerializedName("detailcontent") val detailContent: String? = "",
    @SerializedName("activitytime") val activityTime: String? = "",
    @SerializedName("hostunit") val hostUnit: String? = "",
    @SerializedName("closedate") val closeDate: String? = "",
    @SerializedName("liaisonfax") val liaisonFax: String? = "",
    @SerializedName("postdate") val postDate: String? = "",
    @SerializedName("postunit") val postUnit: String? = "",
    @SerializedName("studyhour") val studyHour: Int? = 0,
    @SerializedName("liaisonper") val liaisonPer: String? = "",
    @SerializedName("liaisontel") val liaisonTel: String? = "",
    @SerializedName("activityaddress") val activityAddress: String? = ""
)

data class File(
    @SerializedName("fileurl") val fileUrl: String? = "",
    @SerializedName("filename") val fileName: String? = ""
)

data class Link(
    @SerializedName("linkname") val linkName: String? = "",
    @SerializedName("linkurl") val linkUrl: String? = ""
)

data class EventImage(
    @SerializedName("imgname") val imgName: String? = "",
    @SerializedName("imgcontent") val imgContent: String? = "",
    @SerializedName("imgurl") val imgUrl: String? = ""
)
