package com.dicoding.vaxi

import retrofit2.Call
import retrofit2.http.GET

interface VaxiServices {

    @GET("vaksinasi")
//    @Headers("Authorization: token ")
    fun getVaksinasi(): Call<VaxiDataResponse>

}