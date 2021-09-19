package com.example.googlemapsdemo.misc

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

class CameraAndViewport {

    val incheon: CameraPosition = CameraPosition.Builder()
        .target(LatLng(37.456257, 126.705208))
        .zoom(17f)
        .bearing(100f)
        .tilt(45f)
        .build()

    val tokyoBounds = LatLngBounds(
        LatLng(34.93377256221988, 138.08985636945425), //SW
        LatLng(36.365856,140.471221) //NE
    )
}