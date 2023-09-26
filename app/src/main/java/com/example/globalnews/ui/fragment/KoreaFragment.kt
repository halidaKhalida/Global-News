package com.example.globalnews.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.View
import android.widget.ProgressBar
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.globalnews.R
import com.example.globalnews.adapter.KoreaAdapter
import com.example.globalnews.data.remote.ApiClient
import com.example.globalnews.model.ArticlesItem
import com.example.globalnews.model.USANews
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class KoreaFragment : Fragment(R.layout.fragment_korea) {
    lateinit var adapterr:KoreaAdapter
    lateinit var koooreaa:ArrayList<ArticlesItem>
    lateinit var loadingProgressBarKorea: ProgressBar
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        loadList()
        koooreaa= ArrayList()
        adapterr= KoreaAdapter()
        val recycleKorea:RecyclerView=view.findViewById(R.id.rv_koreanNews)
        recycleKorea.adapter=adapterr
        recycleKorea.layoutManager=LinearLayoutManager(requireContext())

        adapterr.itemClick={
            val bundle = Bundle()
            bundle.putString("noteId",koooreaa[it].url)
            findNavController().navigate(R.id.action_koreaFragment_to_detailFragment,bundle)
        }
    }

    private fun loadList() {
        ApiClient.apiService.getCtegoryKorea().enqueue(object :Callback<USANews>{
            override fun onResponse(call: Call<USANews>, response: Response<USANews>) {
                if (response.isSuccessful){
                    koooreaa.clear()
                    response.body()?.articles?.forEach {
                        koooreaa.add(it)
                    }
                    adapterr.sublistKorea(koooreaa)
                }
            }

            override fun onFailure(call: Call<USANews>, t: Throwable) {

            }

        })
    }


}