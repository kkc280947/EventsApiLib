package com.example.helloapp.app

import android.app.Application
import com.example.helloapp.app.components.AppComponent
import com.example.helloapp.app.components.DaggerAppComponent

class EventApplication: Application(){

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .build()
    }
}