package com.taskail.parkstashproject.data

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 *Created by ed on 4/11/18.
 */


class LocationRepo(
        private val locationsDao: LocationDao,
        private val disposable: CompositeDisposable
): LocationDataDource {

    private val TAG = javaClass.simpleName

    override fun getLocations(callback: LocationDataDource.LocationsLoadedCallback) {

        disposable.add(getLocationsFromDatabase(locationsDao)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onLocationsLoaded(it)
                }, {
                    callback.onDataNotAvailable(it)
                }
                ))
    }

    override fun saveLocation(location: Location) {

        disposable.add(saveNewLocation(locationsDao, location)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d(TAG, "Saved location success")
                }))
    }

}