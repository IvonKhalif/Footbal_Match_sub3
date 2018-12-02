package com.ivonkhalif.ragnarok.footballlive.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ivonkhalif.ragnarok.footballlive.ItemList
import com.ivonkhalif.ragnarok.footballlive.R
import com.ivonkhalif.ragnarok.footballlive.database.FavoriteMatch
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class FragmentAdapter (private val favoriteMatch: MutableList<FavoriteMatch>, private val listener:(FavoriteMatch)-> Unit): RecyclerView.Adapter<FavoriteMatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMatchViewHolder {
        return FavoriteMatchViewHolder(ItemList().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount() = favoriteMatch.size

    override fun onBindViewHolder(holder: FavoriteMatchViewHolder, position: Int) {
        holder.bindItem(favoriteMatch[position], listener)
    }
}

class FavoriteMatchViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val teamHome: TextView = view.find(R.id.Id_ClubHome)
    private val teamAway: TextView = view.find(R.id.Id_ClubAway)
    private val scoreHome: TextView = view.find(R.id.Id_HomeScore)
    private val scoreAway: TextView = view.find(R.id.Id_AwayScore)
    private val date: TextView = view.find(R.id.Id_date)
    private lateinit var idHome: String
    private lateinit var idAway: String
    private lateinit var match: String

    fun bindItem(favorite: FavoriteMatch, listener: (FavoriteMatch)->Unit) {
        teamHome.text = favorite.strHomeTeam
        teamAway.text = favorite.strAwayTeam
        date.text = favorite.dateEvent
        idHome = favorite.idHomeTeam!!
        idAway = favorite.idAwayTeam!!

//        if (favorite.intHomeScore.equals(null)) scoreHome.text = "-"
//        else scoreHome.text = favorite.intHomeScore.toString()
//        if (favorite.intAwayScore.equals(null)) scoreAway.text = "-"
//        else scoreAway.text = favorite.intAwayScore.toString()

        itemView.setOnClickListener {
            listener(favorite)
        }
    }
}
