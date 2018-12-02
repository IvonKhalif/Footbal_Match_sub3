package com.ivonkhalif.ragnarok.footballlive.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ivonkhalif.ragnarok.footballlive.ItemList
import com.ivonkhalif.ragnarok.footballlive.Model.Event
import com.ivonkhalif.ragnarok.footballlive.R
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class RVAdapterMatch (private var matchList: MutableList<Event>, var listener: (Event) -> Unit): RecyclerView.Adapter<MatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(ItemList().createView(AnkoContext.Companion.create(parent.context,parent)))
    }

    override fun getItemCount() = matchList.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(matchList[position], listener)
    }

}

class MatchViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val teamHome: TextView = view.find(R.id.Id_ClubHome)
    private val teamAway: TextView = view.find(R.id.Id_ClubAway)
    private val scoreHome: TextView = view.find(R.id.Id_HomeScore)
    private val scoreAway: TextView = view.find(R.id.Id_AwayScore)
    private val date: TextView = view.find(R.id.Id_date)
    private lateinit var idHome: String
    private lateinit var idAway: String
    private lateinit var match: String

    fun bindItem(nextMatchList: Event, listener: (Event) -> Unit) {
        teamHome.text= nextMatchList.strHomeTeam
        teamAway.text= nextMatchList.strAwayTeam
        date.text= nextMatchList.dateEvent
        idHome = nextMatchList.idHomeTeam!!
        idAway = nextMatchList.idAwayTeam!!
        match = nextMatchList.strEvent!!

        if(nextMatchList.intHomeScore.equals(null)) scoreHome.text = "-"
        else scoreHome.text = nextMatchList.intHomeScore.toString()
        if (nextMatchList.intAwayScore.equals(null)) scoreAway.text = "-"
        else scoreAway.text = nextMatchList.intAwayScore.toString()

        itemView.setOnClickListener {
            listener(nextMatchList)
        }

    }
}