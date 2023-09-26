package com.example.globalnews.ui.fragment.headlinesfragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.globalnews.R
import com.example.globalnews.adapter.EntertaimentsAdapter
import com.example.globalnews.data.remote.ApiClient
import com.example.globalnews.model.ArticlesItem
import com.example.globalnews.model.USANews
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EntertainmentsFragment : Fragment(R.layout.fragment_entertainments) {
    lateinit var adapteere:EntertaimentsAdapter
    lateinit var entertaimenttt:ArrayList<ArticlesItem>
    lateinit var loadingProgressBarEntertaiment: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        loadList()
        entertaimenttt= ArrayList()
        adapteere= EntertaimentsAdapter()
        val recycleEntertaiments:RecyclerView=view.findViewById(R.id.rv_entertaiments)
        recycleEntertaiments.adapter=adapteere
        recycleEntertaiments.layoutManager=LinearLayoutManager(requireContext())
        adapteere.itemClick={
            val bundle = Bundle()
            bundle.putString("noteId",entertaimenttt[it].url)
            findNavController().navigate(R.id.action_entertainmentsFragment_to_detailFragment,bundle)
        }
    }

    private fun loadList() {
        Log.d("@@@@@@","loaddList:")
        ApiClient.apiService.getCategoryEntertainments().enqueue(object :Callback<USANews>{
            override fun onResponse(call: Call<USANews>, response: Response<USANews>) {
                if (response.isSuccessful){
                    entertaimenttt.clear()
                    Log.d("@@@@@@","onResponce:${response.body()}")
                    response.body()?.articles?.forEach {
                        entertaimenttt.add(it)
                    }
                    adapteere.sublistEntertaiments(entertaimenttt)
                }
            }

            override fun onFailure(call: Call<USANews>, t: Throwable) {

            }

        })
    }

}