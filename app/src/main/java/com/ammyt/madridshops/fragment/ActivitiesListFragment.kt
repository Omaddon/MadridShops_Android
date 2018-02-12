package com.ammyt.madridshops.fragment

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ammyt.domain.model.Activities
import com.ammyt.domain.model.Activity
import com.ammyt.madridshops.R
import com.ammyt.madridshops.adapter.ActivityRecyclerViewAdapter

class ActivitiesListFragment: Fragment() {

    private lateinit var root: View
    private lateinit var activityRecyclerView: RecyclerView
    private var adapter: ActivityRecyclerViewAdapter? = null
    private var onShowActivityDetail: OnShowActivityDetail? = null

    private var activities: Activities? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        inflater?.let {
            root = it.inflate(R.layout.fragment_list, container, false)

            activityRecyclerView = root.findViewById(R.id.recycler_view) as RecyclerView
            activityRecyclerView.layoutManager = GridLayoutManager(activity, resources.getInteger(R.integer.recycler_columns))
            activityRecyclerView.itemAnimator = DefaultItemAnimator()

            adapter = ActivityRecyclerViewAdapter(activities)
            activityRecyclerView.adapter = adapter
        }

        return root
    }

    private fun setListenerToAdapter(adapter: ActivityRecyclerViewAdapter) {
        adapter.onClickListener = View.OnClickListener { v: View? ->
            val position = activityRecyclerView.getChildAdapterPosition(v)
            val activity = activities?.get(position)

            // Send order to navigate to activity container
            onShowActivityDetail?.showActivityDetail(activity!!)
        }
    }

    fun setActivities(activities: Activities) {
        this.activities = activities

        adapter = ActivityRecyclerViewAdapter(activities)
        activityRecyclerView.adapter = adapter

        setListenerToAdapter(adapter!!)

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is OnShowActivityDetail) {
            onShowActivityDetail = context
        }
    }

    override fun onDetach() {
        super.onDetach()

        onShowActivityDetail = null
    }


    /**
     * INTERFACES
     */

    interface OnShowActivityDetail {
        fun showActivityDetail(activity: Activity)
    }

}