package com.example.dogsapp.di.module


import com.example.dogsapp.model.DogsApiService
import com.example.dogsapp.model.DogsModel
import com.example.dogsapp.view.fragments.dogsList.RecyclerAdapter
import dagger.Provides
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

fun dogModule() = Kodein.Module {

    bind<DogsModel>() with singleton { provideDogsModel(instance()) }
    //bind<RecyclerAdapter>() with singleton { provideDateListAdapter() }

}

fun provideDogsModel(numFacApiService: DogsApiService): DogsModel =
    DogsModel(numFacApiService)//

//fun provideDateListAdapter(): RecyclerAdapter = RecyclerAdapter { }//