package com.example.globalnews.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.globalnews.R
import com.example.globalnews.adapter.ItemForYouAdapter
import com.example.globalnews.data.remote.ApiClient
import com.example.globalnews.detail.AddViewModel
import com.example.globalnews.model.ArticlesItem
import com.example.globalnews.model.USANews
import com.example.globalnews.utils.DetailFragmentArgs
import com.example.globalnews.utils.Extensions.hide
import com.example.globalnews.utils.Extensions.isInternetAvailable
import com.example.globalnews.utils.Extensions.show
import com.example.globalnews.utils.UiStateObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ForYouFragment : Fragment(R.layout.fragment_for_you) {
    lateinit var loading: LottieAnimationView
    lateinit var adapterr:ItemForYouAdapter
    lateinit var news:ArrayList<ArticlesItem>
    lateinit var loadingProgressBar: ProgressBar
//    lateinit var viewModel:AddViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
//        ivSearch.setOnClickListener {
//            findNavController().navigate(R.id.action_forYouFragment_to_searchFragment)
//        }



        loadList()
//        viewModel=ViewModelProvider(this).get(AddViewModel::class.java)
        news = ArrayList()
        adapterr = ItemForYouAdapter()

        val recycleView:RecyclerView=view.findViewById(R.id.rv_news_recycleview)
        recycleView.adapter=adapterr
        

        adapterr.itemClick={
            val bundle = Bundle()
            bundle.putString("noteId",news[it].url)
            findNavController().navigate(R.id.action_forYouFragment_to_detailFragment,bundle)
//            findNavController().navigate(R.id.action_forYouFragment_to_detailFragment, bundleOf("noteId" to news[it].url))
        }

    }



    private fun loadList() {
        Log.d("@@@@@@@@","loadList:")

        ApiClient.apiService.getHeadlinesNews().enqueue(object :Callback<USANews>{
            override fun onResponse(call: Call<USANews>, response: Response<USANews>) {
                if (response.isSuccessful){
                    news.clear()
                    response.body()?.articles?.forEach {
                        news.add(it)
                    }
                    adapterr.submitList(news)
                }
            }

            override fun onFailure(call: Call<USANews>, t: Throwable) {

            }

        })
//        ApiClient.apiService.getHeadlinesNews().enqueue(object :retrofit2.Callback<USANews>{
//            override fun onResponse(
//                call: Call<USANews>,
//                response: Response<USANews>
//            ) {
//                if (response.isSuccessful){
//                    news.clear()
//                    Log.d("@@@@@@@@","onResponse:${response.body()}")
//                    response.body()?.articles?.forEach{
//                        news.add(it)
//                    }
//                    adapterr.submitList(news)
//
//                }
//            }
//
//            override fun onFailure(call: Call<USANews>, t: Throwable) {
//
//            }
//
//        })
    }

    fun fetchNews(){

    }


    fun showLoading() {
        loadingProgressBar.visibility = View.VISIBLE
    }

    fun hideLoading() {
        loadingProgressBar.visibility = View.GONE
    }

}