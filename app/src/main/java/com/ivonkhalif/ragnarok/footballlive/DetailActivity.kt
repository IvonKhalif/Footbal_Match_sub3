package com.ivonkhalif.ragnarok.footballlive

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.ivonkhalif.ragnarok.footballlive.Model.Event
import com.ivonkhalif.ragnarok.footballlive.Model.Team
import com.ivonkhalif.ragnarok.footballlive.Model.TeamList
import com.ivonkhalif.ragnarok.footballlive.R.drawable.ic_favourite
import com.ivonkhalif.ragnarok.footballlive.R.drawable.ic_unfavourite
import com.ivonkhalif.ragnarok.footballlive.R.id.add_favorite
import com.ivonkhalif.ragnarok.footballlive.R.menu.detail_menu
import com.ivonkhalif.ragnarok.footballlive.database.FavoriteMatch
import com.ivonkhalif.ragnarok.footballlive.database.database
import com.ivonkhalif.ragnarok.footballlive.rest.ApiInterface
import com.ivonkhalif.ragnarok.footballlive.rest.ApiRepository
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.ctx
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.longToast
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailActivity : AppCompatActivity(), AnkoLogger {

    private lateinit var idHome: String
    private lateinit var idAway: String
    private lateinit var goalHome: String
    private lateinit var goalAway: String
    private lateinit var shotHome: String
    private lateinit var shotAway: String
    private lateinit var gkHome: String
    private lateinit var gkAway: String
    private lateinit var dfHome: String
    private lateinit var dfAway: String
    private lateinit var mfHome: String
    private lateinit var mfAway: String
    private lateinit var fwHome: String
    private lateinit var fwAway: String
    private lateinit var subsHome: String
    private lateinit var subsAway: String
    private lateinit var index: String
    private lateinit var detail: Event

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var id:String? = null
    private var homeTeam: String? = null
    private var awayTeam: String? = null
    private var date:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val bundle = intent.getBundleExtra("myBundle")
//        detail = bundle.getParcelable("selected_match")
        id = bundle.getString("id")
        idHome = bundle.getString("idhome")
        idAway = bundle.getString("idaway")
        homeTeam = bundle.getString("teamhome")
        awayTeam= bundle.getString("teamaway")
        date= bundle.getString("date")

        home_detail.text = homeTeam
        home_detail.isSelected = true
        away_detail.text= awayTeam
        away_detail.isSelected = true
        date_detail.text= date
        idHome = detail.idHomeTeam.toString()
        idAway = detail.idAwayTeam.toString()

        if (detail.intHomeScore.equals(null)) scoreHome.text = "-"
        else scoreHome.text = detail.intHomeScore
        if (detail.intAwayScore.equals(null)) scoreAway.text= "-"
        else scoreAway.text = detail.intAwayScore
        if (detail.intHomeShots.equals(null)) shot_home.text = "-"
        else shot_home.text = detail.intHomeShots
        if (detail.intAwayShots.equals(null)) shot_away.text = "-"
        else shot_away.text = detail.intAwayShots

        goalHome = detail.strHomeGoalDetails.toString().split(";").toString()
            .replace("[","").trim()
            .replace("]","").trim()
            .replace(",",";\n").trim()
            .replace(",",";").trim()
        if (detail.strHomeGoalDetails.equals(null)) goal_home.text ="-"
        else goal_home.text = goalHome.trim()

        goalAway = detail.strAwayGoalDetails.toString().split(";").toString()
            .replace("[","").trim()
            .replace("]","").trim()
            .replace(",",";\n").trim()
            .replace(",",";").trim()
        if (detail.strAwayGoalDetails.equals(null) || detail.strAwayGoalDetails.equals("")) goal_away.text ="-"
        else goal_away.text = goalAway.trim()

        gkHome = detail.strHomeLineupGoalkeeper.toString().split(";").toString()
            .replace("[","").trim()
            .replace("]","").trim()
            .replace(",",";\n").trim()
            .replace(",",";").trim()
        if (detail.strHomeLineupGoalkeeper.equals(null) || detail.strHomeLineupGoalkeeper.equals("")) gk_home.text = "-"
        else gk_home.text= gkHome.trim()

        gkAway = detail.strAwayLineupGoalkeeper.toString().split(";").toString()
            .replace("[","").trim()
            .replace("]","").trim()
            .replace(",",";\n").trim()
            .replace(",",";").trim()
        if (detail.strAwayLineupGoalkeeper.equals(null) || detail.strAwayLineupGoalkeeper.equals("")) gk_away.text = "-"
        else gk_away.text= gkAway.trim()

        dfHome = detail.strHomeLineupDefense.toString().split(";").toString()
            .replace("[","").trim()
            .replace("]","").trim()
            .replace(",",";\n").trim()
            .replace(",",";").trim()
        if (detail.strHomeLineupDefense.equals(null) || detail.strHomeLineupDefense.equals("")) defend_home.text = "-"
        else defend_home.text= dfHome.trim()

        dfAway = detail.strAwayLineupDefense.toString().split(";").toString()
            .replace("[","").trim()
            .replace("]","").trim()
            .replace(",",";\n").trim()
            .replace(",",";").trim()
        if (detail.strAwayLineupDefense.equals(null) || detail.strAwayLineupDefense.equals("")) defense_away.text = "-"
        else defense_away.text= dfAway.trim()

        mfHome = detail.strHomeLineupMidfield.toString().split(";").toString()
            .replace("[","").trim()
            .replace("]","").trim()
            .replace(",",";\n").trim()
            .replace(",",";").trim()
        if (detail.strHomeLineupMidfield.equals(null) || detail.strHomeLineupMidfield.equals("")) mid_home.text = "-"
        else mid_home.text= mfHome.trim()

        mfAway = detail.strAwayLineupMidfield.toString().split(";").toString()
            .replace("[","").trim()
            .replace("]","").trim()
            .replace(",",";\n").trim()
            .replace(",",";").trim()
        if (detail.strAwayLineupMidfield.equals(null) || detail.strAwayLineupMidfield.equals("")) mid_away.text = "-"
        else mid_away.text= mfHome.trim()

        fwHome = detail.strHomeLineupForward.toString().split(";").toString()
            .replace("[","").trim()
            .replace("]","").trim()
            .replace(",",";\n").trim()
            .replace(",",";").trim()
        if (detail.strHomeLineupForward.equals(null) || detail.strHomeLineupForward.equals("")) fw_home.text = "-"
        else fw_home.text= fwHome.trim()

        fwAway = detail.strAwayLineupForward.toString().split(";").toString()
            .replace("[","").trim()
            .replace("]","").trim()
            .replace(",",";\n").trim()
            .replace(",",";").trim()
        if (detail.strAwayLineupForward.equals(null) || detail.strAwayLineupForward.equals("")) fw_away.text = "-"
        else fw_away.text= fwAway.trim()

        subsHome = detail.strHomeLineupSubstitutes.toString().split(";").toString()
            .replace("[","").trim()
            .replace("]","").trim()
            .replace(",",";\n").trim()
            .replace(",",";").trim()
        if (detail.strHomeLineupSubstitutes.equals(null) || detail.strHomeLineupSubstitutes.equals("")) subs_home.text = "-"
        else subs_home.text= subsHome.trim()

        subsAway = detail.strAwayLineupSubstitutes.toString().split(";").toString()
            .replace("[","").trim()
            .replace("]","").trim()
            .replace(",",";\n").trim()
            .replace(",",";").trim()
        if (detail.strHomeLineupSubstitutes.equals(null) || detail.strAwayLineupSubstitutes.equals("")) subs_away.text = "-"
        else subs_away.text= subsAway.trim()

        loadGambarHome()
        loadGambarAway()

        favoriteState()
    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE).whereArgs("(ID_MATCH = {id})", "id" to detail.idEvent.toString())
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun loadGambarHome() {
        val apiSearch = ApiRepository.getRetrofit().create(ApiInterface::class.java)
        val call = apiSearch.getIdTeamHome(idHome)

        call.enqueue(object : Callback<TeamList>{
            override fun onFailure(call: Call<TeamList>, t: Throwable) {}

            override fun onResponse(call: Call<TeamList>, response: Response<TeamList>) {
                if (response.isSuccessful){
                    val team: List<Team> = response.body()?.teams!!
                    for (item : Team? in team.iterator()){
                        Glide.with(ctx).load(item?.strTeamBadge).into(logo_Home)
                    }
                }
            }

        })
    }

    private fun loadGambarAway() {
        val apiSearch = ApiRepository.getRetrofit().create(ApiInterface::class.java)
        val call = apiSearch.getIdTeamAway(idAway)

        call.enqueue(object : Callback<TeamList>{
            override fun onFailure(call: Call<TeamList>, t: Throwable) {}

            override fun onResponse(call: Call<TeamList>, response: Response<TeamList>) {
                if (response.isSuccessful){
                    val team: List<Team> = response.body()?.teams!!
                    for (item : Team? in team.iterator()){
                        Glide.with(ctx).load(item?.strTeamBadge).into(logo_Away)
                    }
                }
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            add_favorite->{
                if(isFavorite) removeFavorite() else addFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else ->{
                super.onOptionsItemSelected(item)
            }

        }
    }

    private fun addFavorite() {
        try {
            database.use {
                insert(FavoriteMatch.TABLE_FAVORITE,
                    FavoriteMatch.ID_MATCH to detail.idEvent,
                    FavoriteMatch.ID_HOME_TEAM to detail.idHomeTeam,
                    FavoriteMatch.ID_AWAY_TEAM to detail.idAwayTeam,
                    FavoriteMatch.NAME_HOME_TEAM to detail.strHomeTeam,
                    FavoriteMatch.NAME_AWAY_TEAM to detail.strAwayTeam,
                    FavoriteMatch.DATE_MATCH to detail.dateEvent)
            }
            toast("Add to Your Favorite").show()
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage).show()
        }
    }

    private fun removeFavorite() {
        try {
            database.use {
                delete(FavoriteMatch.TABLE_FAVORITE, "(ID_MATCH)= {id})", "id" to detail.idEvent.toString())
            }
            longToast("Removed to Favorite").show()
        } catch (e : SQLiteConstraintException){
            longToast(e.localizedMessage).show()
        }

    }

    private fun setFavorite() {
        if(isFavorite){
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_favourite)
        } else{
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_unfavourite)
        }
    }

}
