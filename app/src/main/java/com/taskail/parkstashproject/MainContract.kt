package com.taskail.parkstashproject

import com.taskail.parkstashproject.data.Location


/**
 *Created by ed on 4/11/18.
 */

interface MainContract {

    interface View {

        var presenter: Presenter

    }

    interface Presenter {

        fun getLocations(function: (List<Location>) -> Unit)
    }
}