package com.example.globalnews.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.globalnews.R
import com.example.globalnews.model.ArticlesItem

class KoreaAdapter:RecyclerView.Adapter<KoreaAdapter.KoreaViewHolder>() {
    var itemClickPopular:((Int)->Unit)?=null
    var itemClick:((Int)->Unit)?=null
    private val koreaList=ArrayList<ArticlesItem>()

    fun sublistKorea(koreaListtt:ArrayList<ArticlesItem>){
        this.koreaList.clear()
        this.koreaList.addAll(koreaListtt)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KoreaViewHolder {
        return KoreaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_korea,parent,false))
    }

    override fun getItemCount()=koreaList.size

    override fun onBindViewHolder(holder: KoreaViewHolder, position: Int) {
        val koreaNews=koreaList[position]
        Glide.with(holder.ivKorea).load(koreaNews.urlToImage).into(holder.ivKorea)
        if (koreaNews.author == null){
            holder.tvKoreaAuther.visibility = View.GONE
            holder.tvKoreaTitle.visibility=View.GONE
            holder.ivKorea.visibility=View.GONE
            holder.tvKoreaPublAt.visibility=View.GONE
        }
        if (koreaNews.urlToImage==null){
            holder.ivKorea.visibility=View.GONE
            holder.tvKoreaAuther.visibility = View.GONE
            holder.tvKoreaTitle.visibility=View.GONE
            holder.tvKoreaPublAt.visibility=View.GONE
        }

        holder.tvKoreaAuther.text=koreaNews.author.toString()
        holder.tvKoreaTitle.text=koreaNews.title
        holder.tvKoreaPublAt.text=koreaNews.publishedAt


        holder.llKorea.setOnClickListener{
            itemClick?.invoke(position)
        }

    }
    class KoreaViewHolder(view: View):RecyclerView.ViewHolder(view){
        val ivKorea=view.findViewById<ImageView>(R.id.iv_korea)
        val tvKoreaAuther=view.findViewById<TextView>(R.id.tv_korea_auther)
        val tvKoreaTitle=view.findViewById<TextView>(R.id.tv_korea_title)
        val tvKoreaPublAt=view.findViewById<TextView>(R.id.tv_korea_publishedat)
        val llKorea=view.findViewById<LinearLayout>(R.id.ll_korea)
    }
}