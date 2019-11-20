package com.example.dogsapp.model

import com.example.dogsapp.R
import com.example.dogsapp.entity.Dogs
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class DogsModel(val dogApi : DogsApiService) {

    fun getBreedList(): ArrayList<Dogs> {
        val list = ArrayList<Dogs>()
        list.add(Dogs(R.mipmap.ic_launcher, "affenpinscher", "good boy"))
        list.add(Dogs(R.mipmap.ic_launcher, "african", "good boy"))
        list.add(Dogs(R.mipmap.ic_launcher, "airedale", "good boy"))
        list.add(Dogs(R.mipmap.ic_launcher, "akita", "good boy"))
        list.add(Dogs(R.mipmap.ic_launcher, "basenji", "good boy"))
        list.add(Dogs(R.mipmap.ic_launcher, "appenzeller", "good boy"))
        list.add(Dogs(R.mipmap.ic_launcher, "borzoi", "good boy"))
        list.add(Dogs(R.mipmap.ic_launcher, "brabancon", "good boy"))
        list.add(Dogs(R.mipmap.ic_launcher, "chihuahua", "good boy"))
        list.add(Dogs(R.mipmap.ic_launcher, "dalmatian", "good boy"))
        list.add(Dogs(R.mipmap.ic_launcher, "doberman", "good boy"))
        list.add(Dogs(R.mipmap.ic_launcher, "husky", "good boy"))
        list.add(Dogs(R.mipmap.ic_launcher, "labrador", "good boy"))
        return list
    }
}
