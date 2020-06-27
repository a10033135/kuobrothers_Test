package com.sample.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RepositoryManager {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://data.tycg.gov.tw/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    private val appService: ApiService by lazy { retrofit.create(ApiService::class.java) }

    suspend fun getEventList() = appService.getEvents("https://data.tycg.gov.tw/opendata/datalist/datasetMeta/download?id=a5aa63a9-296c-4b85-b628-62889b700057&rid=3983e8e8-7a67-4bbd-b976-bb0cdb97e2f7")

    suspend fun getSuggest() = appService.getSuggest("https://cloud.culture.tw/frontsite/trans/SearchShowAction.do?method=doFindIssueTypeJ")
}