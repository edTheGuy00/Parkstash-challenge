package com.taskail.parkstashproject.data

/**
 *Created by ed on 4/11/18.
 */

interface LocationDataDource {

    interface LocationsLoadedCallback {

        fun onLocationsLoaded(locations: List<Location>)

        fun onDataNotAvailable(reason: Throwable)
    }

    fun getLocations(callback: LocationsLoadedCallback)

    fun saveLocation(location: Location)
}