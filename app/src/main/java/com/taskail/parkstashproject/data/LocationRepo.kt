package com.taskail.parkstashproject.data

/**
 *Created by ed on 4/11/18.
 */


class LocationRepo(
        val locationsDao: LocationDao
): LocationDataDource {

    override fun getLocations(callback: LocationDataDource.LocationsLoadedCallback) {

    }

    override fun saveLocation(location: Location) {

    }

}