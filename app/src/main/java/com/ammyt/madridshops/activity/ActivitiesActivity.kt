package com.ammyt.madridshops.activity

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.ammyt.domain.interactor.ErrorCompletion
import com.ammyt.domain.interactor.SuccessCompletion
import com.ammyt.domain.interactor.getallactivities.GetAllActivitiesInteractor
import com.ammyt.domain.interactor.getallactivities.GetAllActivitiesInteractorImpl
import com.ammyt.domain.model.Activities
import com.ammyt.domain.model.Activity
import com.ammyt.madridshops.R
import com.ammyt.madridshops.adapter.InfoWindowAdapter
import com.ammyt.madridshops.fragment.ActivitiesListFragment
import com.ammyt.madridshops.router.Router
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_activities.*
import kotlinx.android.synthetic.main.content_activity_list.*

class ActivitiesActivity : AppCompatActivity(), ActivitiesListFragment.OnShowActivityDetail {

    private var listFragment: ActivitiesListFragment? = null
    private var map: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities)
        setSupportActionBar(toolbar)

        downloadActivities()
    }

    private fun downloadActivities() {
        activity_list_progress_bar.visibility = View.VISIBLE

        val getAllActivitiesInteractor: GetAllActivitiesInteractor = GetAllActivitiesInteractorImpl(this)
        getAllActivitiesInteractor.execute(object: SuccessCompletion<Activities> {
            override fun successCompletion(activities: Activities) {
                Log.d("Activities", "❗️Count: " + activities.count())

                listFragment = fragmentManager.findFragmentById(R.id.activity_main_list_fragment_activities) as ActivitiesListFragment?
                listFragment?.setActivities(activities)

                initializeMap(activities)
                activity_list_progress_bar.visibility = View.INVISIBLE
            }

        }, object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                activity_list_progress_bar.visibility = View.INVISIBLE

                AlertDialog.Builder(this@ActivitiesActivity)
                        .setTitle("Error")
                        .setMessage("Conexion Error. Unable connect to server.")
                        .setPositiveButton("Retry?", { dialog, which ->
                            dialog.dismiss()
                            downloadActivities()
                        })
                        .setNegativeButton("Exit", { dialog, which ->
                            finish()
                        })
                        .show()
            }
        })
    }

    private fun initializeMap(activities: Activities) {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.activity_main_map_fragment_activities) as SupportMapFragment
        mapFragment.getMapAsync({
            Log.d("SUCCESS", "ActivityMap loaded!")

            // Madrid center Loc -> lat: 40.416775 | long: -3.703790
            // Better coords to center maps below
            centerMapInPosition(it, 40.4237353, -3.691626)
            it.uiSettings.isRotateGesturesEnabled = false
            it.uiSettings.isZoomControlsEnabled = true
            showUserPosition(baseContext, it)

            it.setInfoWindowAdapter(InfoWindowAdapter(this))

            map = it
            addAllPins(activities)
        })
    }


    private fun centerMapInPosition(map: GoogleMap, latitude: Double, longitude: Double) {
        val coordinate = LatLng(latitude, longitude)
        val cameraPosition = CameraPosition.Builder()
                .target(coordinate)
                .zoom(13f)
                .build()

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    private fun showUserPosition(context: Context, map: GoogleMap) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                    10)

            return
        } else {
            map.isMyLocationEnabled = true
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 10) {
            try {
                map?.isMyLocationEnabled = true
            } catch (e: SecurityException) {

            }
        }
    }

    private fun addAllPins(activities: Activities) {
        for (i in 0 until activities.count()) {
            val activity = activities.get(i)

            if (activity.latitude != null && activity.longitude != null) {
                addPin(map!!, activity)
            }

            map?.setOnInfoWindowClickListener {
                if (it.tag is Activity) {
                    Router().navigateFromActivitiesActivityToActivityDetailActivity(this, it.tag as Activity)
                }
            }
        }
    }

    private fun addPin(map: GoogleMap, activity: Activity) {
        map.addMarker(MarkerOptions()
                .position(LatLng(activity.latitude!!, activity.longitude!!))
                .title(activity.name)
                .snippet(activity.openingHours_es))
                .tag = activity
    }

    /**
     * INTERFACES
     */


    override fun showActivityDetail(activity: Activity) {
        Router().navigateFromActivitiesActivityToActivityDetailActivity(this, activity)
    }

}
