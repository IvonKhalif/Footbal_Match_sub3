package com.ivonkhalif.ragnarok.footballlive.Model

import com.google.gson.annotations.SerializedName

data class EventList(
    @SerializedName("events")
    var events: List<Event>
)