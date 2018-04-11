package com.taskail.parkstashproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), MainContract.Presenter {

    override fun start() {

    }

    lateinit var mainView: MainContract.View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainView = (supportFragmentManager
                .findFragmentById(R.id.mainFragment) as MainContract.View)
                .apply { presenter = this@MainActivity }

    }
}
