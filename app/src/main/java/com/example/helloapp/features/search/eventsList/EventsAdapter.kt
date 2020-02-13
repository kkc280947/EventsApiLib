package com.example.helloapp.features.search.eventsList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eventslibrary.model.Events
import com.example.helloapp.R
import kotlinx.android.synthetic.main.item_events.view.*

class EventsAdapter(val onItemClicked: OnItemClicked): RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    private var eventsList= mutableListOf<Events>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_events,parent,false))
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(eventsList[position])
    }

    fun addEventsToList(newEventsList: MutableList<Events>) {
        eventsList.clear()
        eventsList.addAll(newEventsList)
        notifyDataSetChanged()
    }

    fun clearResults() {
        eventsList.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                onItemClicked.itemClicked(eventsList[adapterPosition])
            }
        }

        @SuppressLint("SetTextI18n")
        fun bindView(events: Events){
            itemView.text_event_title.text= events.title
            val expireTime = events.ttl
            if(expireTime == 0L){
                itemView.text_expire_time.text= itemView.context.getString(R.string.event_expired)
            }else{
                itemView.text_expire_time.text= events.ttl.toString()
            }
            itemView.text_participants.text= itemView.context.getString(R.string.participant_coming) + events.participants
        }
    }

    interface OnItemClicked{
        fun itemClicked(eventInfo: Events)
    }
}