package com.taskail.parkstashproject.data

import java.util.concurrent.Executors

/**
 *Created by ed on 4/11/18.
 */

private val IO_EXECUTORS = Executors.newSingleThreadExecutor()

fun ioThread(function : () -> Unit) {
    IO_EXECUTORS.execute(function)
}