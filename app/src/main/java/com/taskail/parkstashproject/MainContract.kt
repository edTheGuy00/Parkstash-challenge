package com.taskail.parkstashproject

import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.model.MarkerOptions


/**
 *Created by ed on 4/11/18.
 */

interface MainContract {

    interface View {

        var presenter: Presenter

        fun displayLocation(marker: MarkerOptions)

        fun moveCamera(moveTo: CameraUpdate)
    }

    interface Presenter {

        fun getLocations()

        fun handleFabClick()
    }
}