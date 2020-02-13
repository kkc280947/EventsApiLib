package com.example.helloapp.app.modules.datamodule

import com.example.helloapp.features.search.SearchRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule{

    @Provides
    @Singleton
    fun provideSearchRepository(): SearchRepository {
        return SearchRepository()
    }
}