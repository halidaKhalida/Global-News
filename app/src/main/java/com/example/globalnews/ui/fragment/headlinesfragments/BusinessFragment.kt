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
import com.example.globalnews.adapter.BusinessAdapter
import com.example.globalnews.data.remote.ApiClient
import com.example.globalnews.model.ArticlesItem2
import com.example.globalnews.model.Business
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BusinessFragment : Fragment(R.layout.fragment_business) {
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
            val bundle = Bundle()
            bundle.putString("noteId",bisness[it].url)
            findNavController().navigate(R.id.action_businessFragment_to_detailFragment,bundle)
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