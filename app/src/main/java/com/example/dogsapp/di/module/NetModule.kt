package com.example.dogsapp.di.module

import com.example.dogsapp.model.DogsApiService
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun netModule() = Kodein.Module {
    bind<Retrofit>() with singleton { provideRetrofit(instance(), provideRxJava2CallAdapterFactory()) }
    bind<GsonConverterFactory>() with singleton { provideGsonConverterFactory() }
    bind<DogsApiService>() with singleton { instance<Retrofit>().create(DogsApiService::class.java) }
}

    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory =
        RxJava2CallAdapterFactory.create()

    fun provideRetrofit(
        converterFactory: GsonConverterFactory,
        callAdapterFactory: RxJava2CallAdapterFactory
    ): Retrofit =
        Retrofit.Builder()
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(converterFactory)
            .baseUrl("https://dog.ceo/api/breed/")
            .build()



