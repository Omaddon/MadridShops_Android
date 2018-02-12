package com.ammyt.madridshops.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ammyt.domain.model.Activities
import com.ammyt.domain.model.Activity
import com.ammyt.madridshops.R
import com.ammyt.madridshops.utils.getOpeningHours
import com.squareup.picasso.Picasso

class ActivityRecyclerViewAdapter(val activityList: Activities?):
        RecyclerView.Adapter<ActivityRecyclerViewAdapter.ActivityListViewHolder>() {

    var onClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ActivityListViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.content_item_list, parent, false)
        view.setOnClickListener(onClickListener)

        return ActivityListViewHolder(view)
    }

    override fun getItemCount(): Int {
        activityList?.let {
            return it.count()
        }

        return 0
    }

    override fun onBindViewHolder(holder: ActivityListViewHolder?, position: Int) {
        activityList?.let {
            holder?.bindActivity(activityList.get(position))
        }
    }


    inner class ActivityListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val activityImage = itemView.findViewById<ImageView>(R.id.item_logo)
        val activityName = itemView.findViewById<TextView>(R.id.item_name)
        val activityHours = itemView.findViewById<TextView>(R.id.item_hours)

        fun bindActivity(activity: Activity) {
            activityName.text = activity.name

            Picasso.with(itemView.context).setIndicatorsEnabled(true)
            Picasso.with(itemView.context).isLoggingEnabled = true

            Picasso
                    .with(itemView.context)
                    .load(activity.logoURL)
                    .placeholder(R.drawable.no_image)
                    .into(activityImage)

            activityHours.text = getOpeningHours(activity)
        }
    }
}