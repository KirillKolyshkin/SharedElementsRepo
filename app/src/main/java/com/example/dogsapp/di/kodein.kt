package com.example.dogsapp.di

import android.app.Application
import com.example.dogsapp.di.module.appModule
import com.example.dogsapp.di.module.dogModule
import com.example.dogsapp.di.module.netModule
import com.example.dogsapp.vm.BaseViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

lateinit var di: Kodein

fun initKodein(app: Application) {
    di = Kodein {
        import(appModule(app))
        import(netModule())
        import(dogModule())
        bind<BaseViewModelFactory>() with singleton { BaseViewModelFactory(instance()) }
    }
}
