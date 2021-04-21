package com.hafiz.sportworld.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hafiz.sportworld.core.databinding.ItemListSportBinding
import com.hafiz.sportworld.core.domain.model.SportWold
import java.util.*

class SportAdapter : RecyclerView.Adapter<SportAdapter.ListSportViewHolder>() {
    private var listData = ArrayList<SportWold>()
    var onItemClick: ((SportWold) -> Unit)? = null

    fun setData(newListData: List<SportWold>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }
    inner class ListSportViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListSportBinding.bind(itemView)
        fun bind(data: SportWold){
            with(binding){
                Glide.with(itemView.context).load(data.strSportThumb).into(ivItemImage)
                tvItemTitle.text = data.strSport
                tvItemSubtitle.text = data.strFormat
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ListSportViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_list_sport, parent, false))

    override fun onBindViewHolder(holder: ListSportViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size
}