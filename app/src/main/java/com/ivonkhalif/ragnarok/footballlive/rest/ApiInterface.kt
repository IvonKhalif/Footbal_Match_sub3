package com.ivonkhalif.ragnarok.footballlive.rest

import com.ivonkhalif.ragnarok.footballlive.BuildConfig
import com.ivonkhalif.ragnarok.footballlive.Model.EventList
import com.ivonkhalif.ragnarok.footballlive.Model.TeamList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/eventsnextleague.php?id=4328")
    fun getNextMatch(): Call<EventList>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/eventspastleague.php?id=4328")
    fun getPastMatch(): Call<EventList>
//
    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupteam.php/preview")
    fun getIdTeamHome(@Query("id")idHome: String): Call<TeamList>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupteam.php/preview")
    fun getIdTeamAway(@Query("id")idAway: String): Call<TeamList>
}