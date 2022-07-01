package com.hawk.hacky.api


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIRequest {

    @GET ("/v1/latest-news?language=es&apiKey=vGwI1mBU48gg64xa7I7T5TF1AlAEZtngX8wZVtdwGovN0BD8")
    suspend fun getNews() : NewsApiJSON

}