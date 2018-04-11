package com.taskail.parkstashproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.google.android.gms.location.places.AutocompleteFilter
import com.google.android.gms.location.places.Place
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.taskail.googleplacessearchdialog.SimplePlacesSearchDialog
import com.taskail.googleplacessearchdialog.SimplePlacesSearchDialogBuilder
import com.taskail.parkstashproject.data.Location
import com.taskail.parkstashproject.data.LocationDataDource
import com.taskail.parkstashproject.data.LocationRepo
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity(), MainContract.Presenter {

    private val TAG = javaClass.simpleName

    private lateinit var mainView: MainContract.View

    private val database by lazy { (application as PSApplication).database }

    private lateinit var repository: LocationRepo

    private lateinit var disposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        disposable = CompositeDisposable()

        mainView = (supportFragmentManager
                .findFragmentById(R.id.mainFragment) as MainContract.View)
                .apply { presenter = this@MainActivity }


        repository = LocationRepo(database.locationsDao(), disposable)

    }

    override fun getLocations() {

        // Simulate a network request
        val runnable = Runnable {
            kotlin.run {

                repository.getLocations(object : LocationDataDource.LocationsLoadedCallback {
                    override fun onLocationsLoaded(locations: List<Location>) {
                        locations.forEach {
                            val newLocation = LatLng(it.locationLat, it.locationLng)
                            val marker = MarkerOptions().position(newLocation).title(it.locationTitle)
                            mainView.displayLocation(marker)
                        }
                    }

                    override fun onDataNotAvailable(reason: Throwable) {
                        Log.e(TAG, "Data not Available")
                    }
                })

            }
        }
        Handler().postDelayed(runnable, 1000)
    }

    override fun handleFabClick() {
        SimplePlacesSearchDialogBuilder(this)
                .setResultsFilter(AutocompleteFilter.TYPE_FILTER_ESTABLISHMENT)
                .setLatLngBounds(DALLAS_TX_BOUNDS)
                .setSearchHint("Add a new location")
                .setLocationListener(object : SimplePlacesSearchDialog.PlaceSelectedCallback {
                    override fun onPlaceSelected(place: Place) {

                        val moveTo = CameraUpdateFactory.newLatLng(place.latLng)
                        val marker = MarkerOptions().position(place.latLng).title(place.name.toString())

                        mainView.displayLocation(marker)
                        mainView.moveCamera(moveTo)

                        val newLocation = Location(
                                locationTitle = place.name.toString(),
                                locationLat = place.latLng.latitude,
                                locationLng = place.latLng.longitude
                        )

                        repository.saveLocation(newLocation)
                    }
                })
                .build()
                .show()
    }
}
