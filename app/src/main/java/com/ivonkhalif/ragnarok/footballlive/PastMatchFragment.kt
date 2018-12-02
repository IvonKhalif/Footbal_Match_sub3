package com.ivonkhalif.ragnarok.footballlive

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.ivonkhalif.ragnarok.footballlive.Adapter.RVAdapterMatch
import com.ivonkhalif.ragnarok.footballlive.Model.Event
import com.ivonkhalif.ragnarok.footballlive.Model.EventList
import com.ivonkhalif.ragnarok.footballlive.rest.ApiInterface
import com.ivonkhalif.ragnarok.footballlive.rest.ApiRepository
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("DEPRECATION")
class PastMatchFragment : Fragment(), AnkoLogger {

    private lateinit var matchAdapter: RVAdapterMatch
    private val eventM: MutableList<Event> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View = with(container) {

        matchAdapter = RVAdapterMatch(eventM){
            val bundle = Bundle()
            bundle.putParcelable("selected_match", it)
            startActivity(intentFor<DetailActivity>("myBundle" to bundle))
        }

        loadData()

        return UI {
            linearLayout {
                lparams(matchParent, matchParent)
                orientation= LinearLayout.VERTICAL

                swipeRefreshLayout{
                    onRefresh{
                        Handler().postDelayed({
                            setColorSchemeColors(
                                resources.getColor(R.color.colorTosca),
                                resources.getColor(R.color.colorPrimaryDark),
                                resources.getColor(R.color.colorAccent)
                            )
                            loadData()
                            isRefreshing = false
                        }, 2000)
                    }

                    recyclerView {
                        lparams(matchParent, matchParent)
                        layoutManager = LinearLayoutManager(ctx)
                        adapter = matchAdapter
                    }
                }
            }
        }.view
    }

    private fun loadData() {
        val api: ApiInterface = ApiRepository.getRetrofit().create(ApiInterface::class.java)
        val call = api.getPastMatch()
        call.enqueue(object : Callback<EventList> {
            override fun onFailure(call: Call<EventList>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<EventList>, response: Response<EventList>) {
                if (response.isSuccessful()){
                    val event: List<Event> = response.body()?.events!!
                    var message = ""
                    for (item: Event? in event.iterator()){
                        message= message + item?.strEvent+"\n"
                        eventM.clear()
                        eventM.addAll(event)
                        matchAdapter.notifyDataSetChanged()
                    }
                }
            }

        })
    }
}
