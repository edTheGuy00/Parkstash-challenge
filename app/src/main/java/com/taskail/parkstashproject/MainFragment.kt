package com.taskail.parkstashproject

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map_view.*
import kotlinx.android.synthetic.main.include_maps_view.*

/**
 *Created by ed on 4/11/18.
 */

class MainFragment : Fragment(), MainContract.View, OnMapReadyCallback {

    override lateinit var presenter: MainContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_map_view, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        drawerLayout.addDrawerListener(
                drawerToggle()
                        .apply {
                            syncState()
                        }
        )

        (childFragmentManager
                .findFragmentById(R.id.map)
                as SupportMapFragment)
                .apply {
                    getMapAsync(this@MainFragment)
                }

    }

    private fun drawerToggle() : ActionBarDrawerToggle {
        return ActionBarDrawerToggle(activity, drawerLayout, toolbar, R.string.open, R.string.close)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val mockLocation = LatLng(MOCK_LAT, MOCK_LNG)

        with(googleMap) {
            addMarker(MarkerOptions().position(mockLocation).title("Your Location"))
            moveCamera(CameraUpdateFactory.newLatLng(mockLocation))
            animateCamera(CameraUpdateFactory.zoomTo(12.0f))
            uiSettings.isMapToolbarEnabled = false
        }
    }
}