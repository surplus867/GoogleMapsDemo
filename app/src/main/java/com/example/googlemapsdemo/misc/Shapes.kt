package com.example.googlemapsdemo.misc

import android.graphics.Color
import com.example.googlemapsdemo.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import kotlinx.coroutines.delay

class Shapes {

    private val incheon = LatLng(37.456257, 126.705208)
    private val seoul = LatLng(37.532600, 127.024612)
    private val cheongjusi = LatLng(36.63722, 127.48972)
    private val daejeon = LatLng(36.32139, 127.41972)

    private val p0 = LatLng(37.488988838824206, 126.63382720113073)
    private val p1 = LatLng(37.48422667180809, 126.7443424003356)
    private val p2 = LatLng(37.43651183116622, 126.77289529496734)
    private val p3 = LatLng(37.438847404789584, 126.65236663031412)

    private val p00 = LatLng(37.46174223099056, 126.67571257544554)
    private val p01 = LatLng(37.463922325151714, 126.73133086192261)
    private val p02 = LatLng(37.44702493283687, 126.746437063188)
    private val p03 = LatLng(37.44647979176917, 126.6657566552052)

    suspend fun addPolyline(map: GoogleMap){

        //val pattern = listOf(Dot(),Gap(30f))

        val polyline = map.addPolyline(
            PolylineOptions().apply {
                add(incheon, seoul, cheongjusi)
                width(120f)
                color(Color.BLUE)
                geodesic(true)
                jointType(JointType.ROUND)
                startCap(RoundCap())
                endCap(ButtCap())
            }
        )

        delay(5000L)

           polyline.points = listOf(incheon, cheongjusi, daejeon)
    }

    fun addPolygon(map: GoogleMap){
        val polygon = map.addPolygon(
            PolygonOptions().apply {
                add(p0, p1, p2, p3)
                fillColor(R.color.black)
                strokeColor(R.color.black)
                addHole(listOf(p00, p01 , p02, p03))
            }

        )
    }

     fun addCircle(map:GoogleMap){
        val circle = map.addCircle(
            CircleOptions().apply {
                center(incheon)
                radius(5000.0)
                fillColor(R.color.purple_500)
                strokeColor(R.color.purple_500)
            }
        )
    }
}