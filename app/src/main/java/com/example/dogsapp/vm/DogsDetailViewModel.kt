package com.example.dogsapp.vm

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData
import com.example.dogsapp.entity.Dogs
import com.example.dogsapp.model.DogsModel
import io.reactivex.rxkotlin.subscribeBy


class DogsDetailViewModel(private val model: DogsModel) : ViewModel() {
    val showProgress = MutableLiveData<Boolean>()
    val dogBreed = MutableLiveData<String>()
    val dogImg = MutableLiveData<Int>()
    fun setDogBreed(breed: String) = dogBreed.postValue(breed)
    fun setDogImg(img: Int) = dogImg.postValue(img)
}
