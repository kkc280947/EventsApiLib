package com.example.helloapp.features.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.eventslibrary.model.Events
import com.example.helloapp.R
import com.example.helloapp.base.BaseFragment
import com.example.helloapp.features.search.eventsList.EventsAdapter
import kotlinx.android.synthetic.main.fragment_search.*

class  SearchFragment: BaseFragment<SearchViewModel>(), EventsAdapter.OnItemClicked {

    private lateinit var mEventAdapter: EventsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appComponent.inject(this)
        initViewModel(SearchViewModel::class.java)
        initRecyclerView()
        initSearchClick()
        initTextWatcher()

    }

    private fun initRecyclerView() {
        mEventAdapter = EventsAdapter(this)
        recycler_events.adapter = mEventAdapter
    }


    private fun initSearchClick() {
        image_search.setOnClickListener {
            observeEventsResult(edit_text_search.text.toString())
        }
    }

    private fun observeEventsResult(query: String) {
        if(query.isEmpty()) {
            Toast.makeText(context,getString(R.string.enter_word_to_search), Toast.LENGTH_SHORT).show()
        }else{
            viewModel.searchForEvent(query)?.observe(viewLifecycleOwner,
                Observer<MutableList<Events>> { t ->
                    if(!t.isNullOrEmpty()){
                        mEventAdapter.addEventsToList(t)
                    }
                })
        }
    }


    private fun initTextWatcher(){
        edit_text_search.addTextChangedListener( object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.isNullOrEmpty()){
                    mEventAdapter.clearResults()
                }
            }
        })
    }

    override fun itemClicked(eventInfo: Events) {
        getActivityCallback().goToDetails(eventInfo)
    }
}