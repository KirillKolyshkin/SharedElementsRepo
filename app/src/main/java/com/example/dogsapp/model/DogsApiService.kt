package com.example.dogsapp.model

import com.example.dogsapp.entity.Dogs
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsApiService{
    @GET("{breed}/images/random")
    fun getDateInfo(@Path("breed") breed: String): Single<Dogs>
}
