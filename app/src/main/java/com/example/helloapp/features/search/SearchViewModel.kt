package com.example.helloapp.features.search

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eventslibrary.model.Events
import javax.inject.Inject

class SearchViewModel @Inject constructor(var searchRepository: SearchRepository) : ViewModel(){

    @SuppressLint("DefaultLocale")
    fun searchForEvent(query: String): MutableLiveData<MutableList<Events>>? {
        return searchRepository.getEventsResult(query.toLowerCase())
    }
}
