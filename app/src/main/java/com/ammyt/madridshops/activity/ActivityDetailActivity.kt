package com.ammyt.madridshops.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ammyt.domain.model.Activity
import com.ammyt.madridshops.R
import com.ammyt.madridshops.utils.GOOGLE_MAP_URL
import com.ammyt.madridshops.utils.INTENT_ACTIVITY_DETAIL
import com.ammyt.madridshops.utils.getDescription
import com.ammyt.madridshops.utils.getOpeningHours
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_detail.*

class ActivityDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        val activity = intent.getSerializableExtra(INTENT_ACTIVITY_DETAIL) as Activity

        item_name.text =  activity.name
        item_description.text = getDescription(activity)
        item_address.text = activity.address
        item_hours.text = getOpeningHours(activity)

        Picasso
                .with(this)
                .load(activity.imageURL)
                .placeholder(R.drawable.no_image)
                .into(item_image)

        val googleMapUrl = GOOGLE_MAP_URL + activity.latitude + "," + activity.longitude

        Picasso
                .with(this)
                .load(googleMapUrl)
                .placeholder(R.drawable.no_image)
                .into(item_google_map)
    }
}
