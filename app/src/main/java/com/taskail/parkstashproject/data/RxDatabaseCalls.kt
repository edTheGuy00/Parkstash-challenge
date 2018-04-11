package com.taskail.parkstashproject.data

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.annotations.NonNull

/**
 *Created by ed on 4/11/18.
 */

@NonNull
fun getLocationsFromDatabase(@NonNull locationDao: LocationDao) : Observable<List<Location>> {

    return Observable.create { e ->

        val locations = locationDao.getLocations()

        if (locations.isNotEmpty()) {
            e.onNext(locations)
        } else {
            e.onError(Throwable("Data not Available"))
        }
    }
}

fun saveNewLocation(locationDao: LocationDao, location: Location) : Completable {

    return Completable.create{ e ->
        locationDao.addLocation(location)
        e.onComplete()
    }
}