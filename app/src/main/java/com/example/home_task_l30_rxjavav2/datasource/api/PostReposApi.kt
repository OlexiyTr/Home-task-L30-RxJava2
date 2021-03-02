package com.example.home_task_l30_rxjavav2.datasource.api

import com.example.home_task_l30_rxjavav2.datasource.model.PostResponse
import retrofit2.Call
import retrofit2.http.GET

interface PostReposApi {
    @GET("/posts")
    fun getPostsList() : Call<List<PostResponse>>
}