package com.example.dogsapp.vm

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dogsapp.entity.Dogs
import com.example.dogsapp.model.DogsModel

class DogsListViewModel(private val model: DogsModel) : ViewModel() {
    val breedList = MutableLiveData<ArrayList<Dogs>>()

    fun getBreedList(){
        breedList.postValue(model.getBreedList())
    }

}
