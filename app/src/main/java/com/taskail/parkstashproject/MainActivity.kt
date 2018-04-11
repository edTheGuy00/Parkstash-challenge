package com.taskail.parkstashproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

    override fun getLocations(function: (List<Location>) -> Unit) {

        repository.getLocations(object : LocationDataDource.LocationsLoadedCallback {
            override fun onLocationsLoaded(locations: List<Location>) {
                function(locations)
            }

            override fun onDataNotAvailable(reason: Throwable) {
                Log.e(TAG, "Data not Available")
            }
        })
    }
}
