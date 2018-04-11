package com.taskail.parkstashproject.data

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.taskail.parkstashproject.PREPOLUATE_DATA

/**
 *Created by ed on 4/11/18.
 */

@Database(entities = [(Location::class)], version = 1)
abstract class ParkStashDatabase : RoomDatabase() {

    abstract fun locationsDao(): LocationDao

    companion object {

        @Volatile private var databaseInstance: ParkStashDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): ParkStashDatabase {

            synchronized(lock) {
                if (databaseInstance == null) {
                    databaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            ParkStashDatabase::class.java,
                            "ParkStash.db")
                            .addCallback(object : Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)

                                    ioThread {
                                        getInstance(context).locationsDao().prePopulate(PREPOLUATE_DATA)
                                    }
                                }
                            })
                            .build()
                }

                return databaseInstance!!
            }

        }
    }
}