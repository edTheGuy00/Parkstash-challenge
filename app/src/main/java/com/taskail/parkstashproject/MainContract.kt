package com.taskail.parkstashproject

/**
 *Created by ed on 4/11/18.
 */

interface MainContract {

    interface View {

        var presenter: Presenter

    }

    interface Presenter {

        fun start()
    }
}