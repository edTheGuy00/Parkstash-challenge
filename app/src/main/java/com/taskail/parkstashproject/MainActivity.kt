package com.taskail.parkstashproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.taskail.parkstashproject.data.Location

class MainActivity : AppCompatActivity(), MainContract.Presenter {

    lateinit var mainView: MainContract.View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainView = (supportFragmentManager
                .findFragmentById(R.id.mainFragment) as MainContract.View)
                .apply { presenter = this@MainActivity }

    }

    override fun getLocations(locations: List<Location>) {

    }
}
