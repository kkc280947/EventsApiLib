package com.example.helloapp.features.search

import android.os.Bundle
import com.example.eventslibrary.model.Events
import com.example.helloapp.R
import com.example.helloapp.app.components.AppComponent
import com.example.helloapp.app.EventApplication
import com.example.helloapp.base.BaseActivity
import com.example.helloapp.features.IActivityCallback
import com.example.helloapp.features.joinleave.JoinLeaveFragment

class MainActivity : BaseActivity(), IActivityCallback {

    private val appComponent: AppComponent by lazy {
        (application as EventApplication).appComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setContentView(R.layout.activity_main)
        swapFragment(SearchFragment(),false)
    }

    override fun getContainerViewId(): Int {
        return R.id.frame_container
    }

    override fun goToDetails(eventInfo: Events) {
        swapFragment(JoinLeaveFragment.newInstance(eventInfo= eventInfo),false)
    }
}
