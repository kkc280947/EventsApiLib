package com.example.helloapp.app.components

import com.example.helloapp.app.modules.datamodule.DataModule
import com.example.helloapp.features.search.MainActivity
import com.example.helloapp.app.modules.viewmodelmodule.ViewModelModule
import com.example.helloapp.features.joinleave.JoinLeaveFragment
import com.example.helloapp.features.search.SearchFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ViewModelModule::class, DataModule::class]
)
interface AppComponent{
    fun inject(mainActivity: MainActivity)
    fun inject(searchFragment: SearchFragment)
    fun inject(joinLeaveFragment: JoinLeaveFragment)
}