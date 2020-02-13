package com.example.helloapp.features.joinleave

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.helloapp.features.search.SearchRepository
import javax.inject.Inject

class JoinLeaveViewModel @Inject constructor(private val searchRepository: SearchRepository): ViewModel(){

    fun joinLeaveEvent(eventId: Long, joined: Boolean) : MutableLiveData<Boolean>{
        return if(joined){
            searchRepository.leaveEvent(eventId)
        }else{
            searchRepository.joinEvent(eventId)
        }
    }
}
