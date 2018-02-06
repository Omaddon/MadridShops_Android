package com.ammyt.madridshops.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.ammyt.domain.interactor.ErrorCompletion
import com.ammyt.domain.interactor.SuccessCompletion
import com.ammyt.domain.interactor.getallshops.GetAllShopsInteractor
import com.ammyt.domain.interactor.getallshops.GetAllShopsInteractorImpl
import com.ammyt.domain.model.Shops
import com.ammyt.madridshops.R
import com.ammyt.madridshops.fragment.ListFragment
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var listFragment: ListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        Log.d("App", "onCreate MainActivity")

        setupMap()

        listFragment = supportFragmentManager.findFragmentById(R.id.activity_main_list_fragment) as ListFragment

    }

    private fun setupMap() {
        var getAllShopsInteractor: GetAllShopsInteractor = GetAllShopsInteractorImpl(this)
        getAllShopsInteractor.execute(object: SuccessCompletion<Shops> {
            override fun successCompletion(shops: Shops) {
                initializeMap()
            }

        }, object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Toast.makeText(baseContext, "Error loading.", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun initializeMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.activity_main_map_fragment) as SupportMapFragment
        mapFragment.getMapAsync({
            Log.d("SUCCESS", "HABEMUS MAPA")
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
