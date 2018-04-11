package com.taskail.parkstashproject.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 *Created by ed on 4/11/18.
 */

@Entity(tableName = "locations")
data class Location @JvmOverloads constructor(
        @ColumnInfo(name = "loccation_title") var locationTitle: String = "",
        @ColumnInfo(name = "location_lat") var locationLat: Double,
        @ColumnInfo(name = "location_lng") var locationLng: Double,
        @PrimaryKey @ColumnInfo(name = "entry_id") var id: String = UUID.randomUUID().toString()
)