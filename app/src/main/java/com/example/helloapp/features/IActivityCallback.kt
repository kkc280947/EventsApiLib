package com.example.helloapp.features

import com.example.eventslibrary.model.Events

interface IActivityCallback {

    fun goToDetails(eventInfo: Events)
}