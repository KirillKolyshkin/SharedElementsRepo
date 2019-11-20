package com.example.dogsapp.view.fragments.dogsList

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.dogsapp.R
import com.example.dogsapp.entity.Dogs
import kotlinx.android.synthetic.main.number_item.view.*

class RecyclerAdapter(private var onItemClick: (Dogs, TextView, ImageView) -> Unit) :
    RecyclerView.Adapter<ViewHolder>() {

    var list: ArrayList<Dogs> = arrayListOf()
    override fun getItemCount() = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.number_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(list[position])
        ViewCompat.setTransitionName(holder.itemView.tv_breed, position.toString() + "Text")
        ViewCompat.setTransitionName(holder.itemView.iv_dog_img, (position).toString() + "Img")
        holder.itemView.setOnClickListener {
            onItemClick(
                list[position],
                holder.itemView.tv_breed,
                holder.itemView.iv_dog_img
            )
        }
    }
}
