package com.ivonkhalif.ragnarok.footballlive

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.ivonkhalif.ragnarok.footballlive.Adapter.FragmentAdapter
import com.ivonkhalif.ragnarok.footballlive.database.FavoriteMatch
import com.ivonkhalif.ragnarok.footballlive.database.database
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*


@Suppress("DEPRECATION")
class FavoriteFragment : Fragment(), AnkoLogger {
    private lateinit var matchFavoriteAdapter : FragmentAdapter
    private val favoritelist: MutableList<FavoriteMatch> = mutableListOf()
//    private lateinit var progressBar: ProgressBar
//    private lateinit var swipeRefresh: SwipeRefreshLayout

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//
//    }
//
//    override fun onResume() {
//        super.onResume()
//        getFavorited()
//    }
//
//    fun showFavoriteList(data: List<FavoriteMatch>) {
//        favoritelist.clear()
//        favoritelist.addAll(data)
//        matchFavoriteAdapter.notifyDataSetChanged()
//        swipeRefresh.isRefreshing= false
//    }
//
//    fun getFavorited(){
//        context?.database?.use {
//            val result = select(FavoriteMatch.TABLE_FAVORITE)
//            val favoritee= result.parseList(classParser<FavoriteMatch>())
//            showFavoriteList(favoritee)
//        }
//    }

//    @SuppressLint("ResourceType")
//    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
//        linearLayout {
//            lparams(matchParent, wrapContent)
//            topPadding= dip(16)
//            rightPadding= dip(16)
//            leftPadding= dip(16)
//            orientation= LinearLayout.VERTICAL
//
//            swipeRefreshLayout {
//                onRefresh {
//                    getFavorited()
//                }
//
//                setColorSchemeResources(
//                    resources.getColor(R.color.colorTosca),
//                    resources.getColor(R.color.colorPrimaryDark),
//                    resources.getColor(R.color.colorAccent)
//                )
//
//                recyclerView {
//                    lparams(matchParent, wrapContent)
//                    layoutManager= LinearLayoutManager(ctx)
//                    adapter= matchFavoriteAdapter
//                }
//            }
//        }
//    }


    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        matchFavoriteAdapter = FragmentAdapter(favoritelist){
            val bundle = Bundle()
            bundle.putString("id",it.idEvent)
            bundle.putString("idhome",it.idHomeTeam)
            bundle.putString("idaway",it.idAwayTeam)
            bundle.putString("teamhome",it.strHomeTeam)
            bundle.putString("teamaway",it.strAwayTeam)
            bundle.putString("date",it.dateEvent )
            startActivity(intentFor<DetailActivity>("myBundle" to bundle))
        }

        loadSQLData()

        return UI{
        linearLayout {
            lparams(matchParent, wrapContent)
            topPadding= dip(16)
            rightPadding= dip(16)
            leftPadding= dip(16)
            orientation= LinearLayout.VERTICAL

            swipeRefreshLayout {
                onRefresh {
//                    setColorSchemeResources(
//                            resources.getColor(R.color.colorTosca),
//                            resources.getColor(R.color.colorPrimaryDark),
//                            resources.getColor(R.color.colorAccent)
//                        )
//                        loadSQLData()
//                    isRefreshing = false

                    Handler().postDelayed({
//                        setColorSchemeColors(
//                            resources.getColor(R.color.colorTosca),
//                            resources.getColor(R.color.colorPrimaryDark),
//                            resources.getColor(R.color.colorAccent)
//                        )
                        loadSQLData()
                        isRefreshing = false
                    }, 2000)


                }

                recyclerView {
                    lparams(matchParent, wrapContent)
                    layoutManager= LinearLayoutManager(ctx)
                    adapter= matchFavoriteAdapter
                }
            }
        }
    }.view
    }
    private fun loadSQLData() {
        context?.database?.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE)
            val favoritee= result.parseList(classParser<FavoriteMatch>())
            favoritelist.clear()
            favoritelist.addAll(favoritee)
            matchFavoriteAdapter.notifyDataSetChanged()
        }
    }

}
