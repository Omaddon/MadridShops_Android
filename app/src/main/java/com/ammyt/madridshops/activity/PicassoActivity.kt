package com.ammyt.madridshops.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ammyt.madridshops.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_picasso.*

class PicassoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picasso)

        Picasso.with(this).setIndicatorsEnabled(true)
        Picasso.with(this).isLoggingEnabled = true

        Picasso
                .with(this)
                .load("https://images.fun.com/products/36979/1-1/doctor-who-tardis-standup.jpg")
                .placeholder(android.R.drawable.ic_delete)
                .into(img1)

        Picasso
                .with(this)
                .load("https://static.giantbomb.com/uploads/original/0/5370/1342322-amy_pond.jpg")
                .placeholder(android.R.drawable.ic_delete)
                .into(img2)

        Picasso
                .with(this)
                .load("https://images-na.ssl-images-amazon.com/images/I/71%2BpxekG3dL._SL1500_.jpg")
                .placeholder(android.R.drawable.ic_delete)
                .into(img3)
    }
}
