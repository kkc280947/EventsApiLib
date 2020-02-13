package com.example.helloapp.features.joinleave

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.eventslibrary.model.Events
import com.example.helloapp.R
import com.example.helloapp.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_join_leave.*

class JoinLeaveFragment : BaseFragment<JoinLeaveViewModel>() {

    companion object {
        private const val ARGS_EVENT = "event_info"
        fun newInstance(eventInfo: Events): JoinLeaveFragment {
            val bundle = Bundle()
            bundle.putParcelable(ARGS_EVENT, eventInfo)
            val fragment = JoinLeaveFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var isJoined = false
    private var eventId = 0L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_join_leave, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appComponent.inject(this)
        initViewModel(JoinLeaveViewModel::class.java)
        initEventUI()
        initClickEvent()
    }

    private fun initClickEvent() {
        text_join.setOnClickListener {
            if (eventId != 0L) {
                viewModel.joinLeaveEvent(eventId,isJoined)
                    .observe(viewLifecycleOwner, Observer<Boolean> { t ->
                        if (t) {
                            isJoined = !isJoined
                            if (isJoined) {
                                text_join.text = getString(R.string.leave_group)
                            } else {
                                text_join.text = getString(R.string.join_group)
                            }
                        }
                    })
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initEventUI() {
        val eventInfo: Events? = arguments?.getParcelable(ARGS_EVENT)
        this.eventId = eventInfo?.id ?: 0L

        text_event_title.text = eventInfo?.title

        val expireTime = eventInfo?.ttl
        if (expireTime == 0L) {
            text_expire_time.text = context?.getString(R.string.event_expired)
        } else {
            text_expire_time.text = eventInfo?.ttl.toString()
        }
        text_participants.text =
            "${context?.getString(R.string.participant_coming)}${eventInfo?.participants}"
        if (expireTime != 0L && !isJoined) {
            text_join.text = "Join"
        }
    }
}
