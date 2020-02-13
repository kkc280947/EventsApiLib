package com.example.helloapp.features.search

import androidx.lifecycle.MutableLiveData
import com.example.eventslibrary.EventLib
import com.example.eventslibrary.model.Events

class SearchRepository {

    fun getEventsResult(query: String): MutableLiveData<MutableList<Events>>? {
        val eventsResultData = MutableLiveData<MutableList<Events>>()
        Thread{
            eventsResultData.postValue(EventLib.searchForEvent(query))
        }.start()
        return eventsResultData
    }

    fun joinEvent(eventId: Long): MutableLiveData<Boolean> {
        val joinEventSuccessResult = MutableLiveData<Boolean>()
        Thread{
            joinEventSuccessResult.postValue(EventLib.joinForEvent(eventId.toString()))
        }.start()
        return joinEventSuccessResult
    }

    fun leaveEvent(eventId: Long): MutableLiveData<Boolean> {
        val joinEventSuccessResult = MutableLiveData<Boolean>()
        Thread{
            joinEventSuccessResult.postValue(EventLib.leaveEvent(eventId.toString()))
        }.start()
        return joinEventSuccessResult
    }
}