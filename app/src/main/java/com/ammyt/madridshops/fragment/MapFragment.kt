package com.ammyt.madridshops.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ammyt.domain.model.Shops

import com.ammyt.madridshops.R


class MapFragment : Fragment() {

    fun setShops(shops: Shops) {

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_map, container, false)
    }

}
