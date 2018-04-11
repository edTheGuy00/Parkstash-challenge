package com.taskail.parkstashproject.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

/**
 *Created by ed on 4/11/18.
 */

@Dao
interface LocationDao {

    /**
     * get all locations from the locations database table
     * @return a list of locations
     */
    @Query("SELECT * FROM Locations") fun getLocations(): List<Location>

    /**
     * insert a new location into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)fun addLocation(location: Location)

    @Insert
    fun prePopulate(data: List<Location>)
}