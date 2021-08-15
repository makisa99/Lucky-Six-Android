package com.mario.lakisiks

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiRest {
    @POST("/cs330/")
    suspend fun rawJSON(@Body requestBody: RequestBody)
}