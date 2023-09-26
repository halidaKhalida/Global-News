package com.example.globalnews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.globalnews.data.remote.ApiClient
import com.example.globalnews.model.ArticlesItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForYouViewModel:ViewModel() {
//    val allNews=MutableLiveData<ArrayList<ArticlesItem>>()
//    val deleteNews=MutableLiveData<ArticlesItem>()
//    fun apiGetAllNews():LiveData<ArrayList<ArticlesItem>>{
//        ApiClient.apiService.getHeadlinesNews().enqueue(object :Callback<Headlines>{
//            override fun onResponse(
//                call: Call<Headlines>,
//                response: Response<Headlines>
//            ) {
//                if (response.isSuccessful){
//                    allNews.value=response.body(
//                }
//            }
//
//            override fun onFailure(call: Call<Headlines>, t: Throwable) {
//                allNews.value= ArrayList()
//            }
//
//        })
//        return allNews
//    }

}