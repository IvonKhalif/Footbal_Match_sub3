package com.ivonkhalif.ragnarok.footballlive.Model

import com.google.gson.annotations.SerializedName

data class TeamList(
    @SerializedName("teams")
    var teams: List<Team>
)