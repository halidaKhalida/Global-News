package com.example.globalnews.ui.fragment.headlinesfragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.globalnews.R
import com.example.globalnews.adapter.BusinessAdapter
import com.example.globalnews.data.remote.ApiClient
import com.example.globalnews.model.ArticlesItem2
import com.example.globalnews.model.Business
import com.example.globalnews.navigation.MyNavigator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BusinessFragment(val listener:MyNavigator) : Fragment(R.layout.fragment_business) {
    lateinit var adapteer:BusinessAdapter
    lateinit var bisness:ArrayList<ArticlesItem2>
    lateinit var loadingProgresssBarBusness:ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        loadList()
        bisness= ArrayList()
        adapteer= BusinessAdapter()
        val recyclelBissnes:RecyclerView=view.findViewById(R.id.rv_business)
        recyclelBissnes.adapter=adapteer
        recyclelBissnes.layoutManager=LinearLayoutManager(requireContext())

        adapteer.itemClickk={

           listener.saveAction(
               R.id.action_headlinesFragment_to_detailFragment,
               bundleOf("noteId" to bisness[it].url)
           )

        }
    }

    private fun loadList() {
        val country="ru"
        Log.d("@","loadList:")
        ApiClient.apiService.getCategoryBusiness().enqueue(object : Callback<Business>{
            override fun onResponse(
                call: Call<Business>,
                response: Response<Business>
            ) {
                if (response.isSuccessful){
                    bisness.clear()
                    Log.d("@","OnResponse:${response.body()}")
                    response.body()?.articles?.forEach{
                        bisness.add(it)
                    }
                    adapteer.sublistBusiness(bisness)
                }
            }

            override fun onFailure(call: Call<Business>, t: Throwable) {

            }

        })
    }


}