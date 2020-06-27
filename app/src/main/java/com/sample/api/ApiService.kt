package com.sample.api

import com.sample.module.Event
import com.sample.module.Suggest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getEvents(@Url url: String): List<Event>

    @GET
    suspend fun getSuggest(@Url url: String): Suggest

}