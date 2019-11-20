package com.example.dogsapp.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dogsapp.model.DogsModel

class BaseViewModelFactory(private val dogsModel: DogsModel) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(DogsDetailViewModel::class.java) -> {
                DogsDetailViewModel(dogsModel) as? T
                    ?: throw IllegalArgumentException("Unknown ViewModel class")
            }
            else -> {
                when {
                    modelClass.isAssignableFrom(DogsListViewModel::class.java) -> {
                        DogsListViewModel(dogsModel) as? T
                            ?: throw IllegalArgumentException("Unknown ViewModel class")
                    }
                    else -> {
                        throw IllegalArgumentException("Unknown ViewModel class")
                    }
                }
            }
        }
}

