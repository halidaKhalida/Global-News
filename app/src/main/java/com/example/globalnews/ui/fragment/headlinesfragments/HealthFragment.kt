package com.example.globalnews.ui.fragment.headlinesfragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.globalnews.R
import com.example.globalnews.adapter.HealthAdapter
import com.example.globalnews.data.remote.ApiClient
import com.example.globalnews.model.ArticlesItem
import com.example.globalnews.model.USANews
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HealthFragment : Fragment(R.layout.fragment_health) {
    lateinit var adaptereed:HealthAdapter
    lateinit var healthh:ArrayList<ArticlesItem>
    lateinit var loadingProgressBarHealth:ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        loadList()
        healthh= ArrayList()
        adaptereed= HealthAdapter()
        val recyclerViewHealth:RecyclerView=view.findViewById(R.id.rv_inHealth)
        recyclerViewHealth.adapter=adaptereed
        recyclerViewHealth.layoutManager=LinearLayoutManager(requireContext())

        adaptereed.itemClick={
            val bundle = Bundle()
            bundle.putString("noteId",healthh[it].url)
            findNavController().navigate(R.id.action_healthFragment_to_detailFragment,bundle)
        }
    }

    private fun loadList() {
        Log.d("@@","loooaadList:")
        ApiClient.apiService.getCategoryHealth().enqueue(object :Callback<USANews>{
            override fun onResponse(call: Call<USANews>, response: Response<USANews>) {
                if (response.isSuccessful){
                    healthh.clear()
                    Log.d("@@","onResponce:${response.body()}")
                    response.body()?.articles?.forEach {
                        healthh.add(it)
                    }
                    adaptereed.sublistHealth(healthh)
                }
            }

            override fun onFailure(call: Call<USANews>, t: Throwable) {

            }

        })
    }


}