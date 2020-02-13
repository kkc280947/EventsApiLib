package com.example.helloapp.app.modules.viewmodelmodule

import androidx.lifecycle.ViewModel
import com.example.helloapp.features.joinleave.JoinLeaveViewModel
import com.example.helloapp.features.search.SearchViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule{

    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
    @Retention(AnnotationRetention.RUNTIME)
    @MapKey
    annotation class ViewModelKey(val value: KClass<out ViewModel>)

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(myViewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(JoinLeaveViewModel::class)
    abstract fun bindJoinLeaveViewModel(myViewModel: JoinLeaveViewModel): ViewModel
}