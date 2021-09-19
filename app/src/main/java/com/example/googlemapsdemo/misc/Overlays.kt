package com.example.googlemapsdemo.misc

import com.example.googlemapsdemo.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*

class Overlays {

    private val incheon = LatLng(37.456257, 126.705208)
    private val incheonBounds = LatLngBounds(
        LatLng(37.391384,126.651617),
        LatLng(37.52984010433299, 126.78660781558345)

    )

    fun addGroundOverlay(map: GoogleMap): GroundOverlay{
        return map.addGroundOverlay(
            GroundOverlayOptions().apply {
                positionFromBounds(incheonBounds)
                image(BitmapDescriptorFactory.fromResource(R.drawable.custom_marker))
            }
        )
    }

    fun addGroundOverlayWithTag(map: GoogleMap): GroundOverlay{
        val groundOverlay = map.addGroundOverlay(
            GroundOverlayOptions().apply {
                positionFromBounds(incheonBounds)
                image(BitmapDescriptorFactory.fromResource(R.drawable.custom_marker))
            }
        )
        groundOverlay.tag = "Minyu"
        return groundOverlay
    }
}