package com.taskail.parkstashproject.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 *Created by ed on 4/11/18.
 */

@Entity(tableName = "locations")
data class Location(
        var locationTitle: String,
        var locationLat: Double,
        var locationLng: Double,
        @PrimaryKey var id: String = UUID.randomUUID().toString()
)