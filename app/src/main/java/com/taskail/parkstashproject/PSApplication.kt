package com.taskail.parkstashproject

import android.app.Application
import com.taskail.parkstashproject.data.ParkStashDatabase

/**
 *Created by ed on 4/11/18.
 */


class PSApplication : Application() {

    lateinit var database: ParkStashDatabase

    override fun onCreate() {
        super.onCreate()

        database = ParkStashDatabase.getInstance(this)
    }
}