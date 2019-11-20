package com.example.dogsapp.view.fragments.dogsList

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dogsapp.entity.Dogs
import kotlinx.android.synthetic.main.number_item.view.*

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindViews(dog: Dogs) {
        with(itemView) {
            tv_breed.text = dog.status
            iv_dog_img.setImageResource(dog.uri)
        }
    }
}
