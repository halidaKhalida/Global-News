package com.example.globalnews.ui.fragment.headlinesfragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.globalnews.R
import com.example.globalnews.adapter.SportAdapter
import com.example.globalnews.data.remote.ApiClient
import com.example.globalnews.model.ArticlesItem
import com.example.globalnews.model.USANews
import com.example.globalnews.navigation.MyNavigator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SportFragment(val listener: MyNavigator) : Fragment(R.layout.fragment_sport) {
    lateinit var adapteer:SportAdapter
    lateinit var spooorttt:ArrayList<ArticlesItem>
    lateinit var loadingProgressBarSport: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        loadList()
        spooorttt= ArrayList()
        adapteer= SportAdapter()
        val recycleSport:RecyclerView=view.findViewById(R.id.rv_sport)
        recycleSport.adapter=adapteer
        recycleSport.layoutManager=LinearLayoutManager(requireContext())
        adapteer.itemClick={
            listener.saveAction(
                R.id.action_headlinesFragment_to_detailFragment,
                bundleOf("noteId" to spooorttt[it].url)
            )
        }
    }

    private fun loadList() {
        Log.d("@@@@@@@","loadLiiiist:")
        ApiClient.apiService.getCategorySport().enqueue(object :Callback<USANews>{
            override fun onResponse(call: Call<USANews>, response: Response<USANews>) {
                if (response.isSuccessful){
                    spooorttt.clear()
                    Log.d("@@@@@@@","onResponce:${response.body()}:")
                    response.body()?.articles?.forEach {
                        spooorttt.add(it)
                    }
                    adapteer.sublistSport(spooorttt)
                }
            }

            override fun onFailure(call: Call<USANews>, t: Throwable) {

            }

        })
    }

}