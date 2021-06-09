package com.dicoding.vaxi

import retrofit2.Call
import retrofit2.http.GET

interface VaxiServices {

    @GET("vaxi2")
//    @Headers("Authorization: token ")
    fun getVaksinasi(): Call<VaxiDataResponse>

}