package com.ivonkhalif.ragnarok.footballlive.rest

import com.ivonkhalif.ragnarok.footballlive.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRepository {
    companion object {
        fun getRetrofit() : Retrofit{
            return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}