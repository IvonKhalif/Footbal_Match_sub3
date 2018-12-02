package com.ivonkhalif.ragnarok.footballlive.database

data class FavoriteMatch(val id: Long?, val idEvent: String?, val idHomeTeam: String?,val idAwayTeam: String?,
                         val strHomeTeam:String?, val strAwayTeam: String?,
                         val dateEvent: String?){

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val ID_MATCH: String = "ID_MATCH"
        const val ID_HOME_TEAM: String = "ID_HOME_TEAM"
        const val ID_AWAY_TEAM: String = "ID_AWAY_TEAM"
        const val NAME_HOME_TEAM: String = "NAME_HOME_TEAM"
        const val NAME_AWAY_TEAM: String = "NAME_AWAY_TEAM"
        const val DATE_MATCH: String = "DATE_MATCH"
    }
}