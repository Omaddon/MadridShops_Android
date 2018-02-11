package com.ammyt.madridshops.activity

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
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
import com.ammyt.domain.interactor.getallshops.GetAllShopsInteractor
import com.ammyt.domain.interactor.getallshops.GetAllShopsInteractorImpl
import com.ammyt.domain.model.Shop
import com.ammyt.domain.model.Shops
import com.ammyt.madridshops.R
import com.ammyt.madridshops.adapter.InfoWindowAdapter
import com.ammyt.madridshops.fragment.ShopsListFragment
import com.ammyt.madridshops.router.Router
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_shop.*
import kotlinx.android.synthetic.main.content_shop.*

// TODO ActivitiesActivity
class ShopActivity : AppCompatActivity(), ShopsListFragment.OnShowShopDetail {

    private var listFragment: ShopsListFragment? = null
    private var map: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)
        setSupportActionBar(toolbar)

        downloadShops()
    }

    private fun downloadShops() {
        shop_list_progress_bar.visibility = View.VISIBLE

        val getAllShopsInteractor: GetAllShopsInteractor = GetAllShopsInteractorImpl(this)
        getAllShopsInteractor.execute(object: SuccessCompletion<Shops> {
            override fun successCompletion(shops: Shops) {
                Log.d("Shops", "❗️Count: " + shops.count())

                listFragment = fragmentManager.findFragmentById(R.id.activity_main_list_fragment) as ShopsListFragment?
                listFragment?.setShops(shops)

                initializeMap(shops)
                shop_list_progress_bar.visibility = View.INVISIBLE
            }

        }, object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                shop_list_progress_bar.visibility = View.INVISIBLE

                AlertDialog.Builder(this@ShopActivity)
                        .setTitle("Error")
                        .setMessage("Conexion Error. Unable connect to server.")
                        .setPositiveButton("Retry?", { dialog, which ->
                            dialog.dismiss()
                            downloadShops()
                        })
                        .setNegativeButton("Exit", { dialog, which ->
                            finish()
                        })
                        .show()
            }
        })
    }

    private fun initializeMap(shops: Shops) {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.activity_main_map_fragment) as SupportMapFragment
        mapFragment.getMapAsync({
            Log.d("SUCCESS", "ShopMap loaded!")

            // Madrid center Loc -> lat: 40.416775 | long: -3.703790
            // Better coords to center maps below
            centerMapInPosition(it, 40.4237353, -3.691626)
            it.uiSettings.isRotateGesturesEnabled = false
            it.uiSettings.isZoomControlsEnabled = true
            showUserPosition(baseContext, it)

            it.setInfoWindowAdapter(InfoWindowAdapter(this))

            map = it
            addAllPins(shops)
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
        if (ActivityCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION),
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

    private fun addAllPins(shops: Shops) {
        for (i in 0 until shops.count()) {
            val shop = shops.get(i)

            if (shop.latitude != null && shop.longitude != null) {
                addPin(map!!, shop)
            }

            map?.setOnInfoWindowClickListener {
                if (it.tag is Shop) {
                    Router().navigateFromShopActivityToShopDetailActivity(this, it.tag as Shop)
                }
            }
        }
    }

    private fun addPin(map: GoogleMap, shop: Shop) {
        map.addMarker(MarkerOptions()
                .position(LatLng(shop.latitude!!,shop.longitude!!))
                .title(shop.name)
                .snippet(shop.openingHours_es))
                .tag = shop
    }

    /**
     * INTERFACES
     */

    override fun showShopDetail(shop: Shop) {
        Router().navigateFromShopActivityToShopDetailActivity(this, shop)
    }
}
