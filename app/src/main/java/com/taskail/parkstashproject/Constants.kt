package com.taskail.parkstashproject
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.taskail.parkstashproject.data.Location

/**
 *Created by ed on 4/11/18.
 */


const val MOCK_LAT = 32.7941983
const val MOCK_LNG = -96.7655

val DALLAS_TX_BOUNDS = LatLngBounds(LatLng(32.694865977875054, -96.85546875), LatLng(32.84267363195431, -96.6796875))

val PREPOPULATE_DATA = listOf(
        Location(
                locationTitle = "The DEC",
                locationLat = 32.7811781,
                locationLng = -96.809032
        ),

        Location(
                locationTitle = "Deep Sushi",
                locationLat = 32.7841842,
                locationLng = -96.7877744
        ),

        Location(
                locationTitle = "Baylor University Medical Center",
                locationLat = 32.784102,
                locationLng = -96.7920399
        )
)