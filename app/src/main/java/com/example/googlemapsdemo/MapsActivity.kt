package com.example.googlemapsdemo

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.googlemapsdemo.databinding.ActivityMapsBinding
import com.example.googlemapsdemo.misc.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.data.geojson.GeoJsonLayer
import com.google.maps.android.heatmaps.Gradient
import com.google.maps.android.heatmaps.HeatmapTileProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private val incheon = LatLng(37.456257, 126.705208)

    private val typeAndStyle by lazy { TypeAndStyle() }
    private val cameraAndViewport by lazy { CameraAndViewport() }
    private val shapes by lazy { Shapes() }
    private val overlays by lazy { Overlays() }

    private val locationList = listOf(
        LatLng(37.517235,127.047325),
        LatLng(37.533432,126.979088),
        LatLng(36.351002, 127.385002),
        LatLng(36.63722,127.48972),
        LatLng(35.866669, 128.600006),
        LatLng(35.166668,129.066666),
        LatLng(33.50972,126.52194),
        LatLng(37.3705, 128.3903),
        LatLng(35.549999,129.316666),
        LatLng(36.56556, 128.725),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_types_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        typeAndStyle.setMapType(item, map)
        return true
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        // Add a marker in Sydney and move the camera
        val incheonMarker =
            map.addMarker(
                MarkerOptions()
                .position(incheon)
                .title("Marker in Incheon")
                .snippet("김혜수"))

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(incheon, 10f))
        map.uiSettings.apply {
            isZoomControlsEnabled = true
            isMyLocationButtonEnabled = true
        }

        typeAndStyle.setMapStyle(map, this)

        addHeatMap()

        }

    private fun addHeatMap(){

        val colors = intArrayOf(
            Color.rgb(0, 128,255), // Blue
            Color.rgb(204,0, 204), // Pink
            Color.rgb(255, 255, 51) // Yellow
        )

        val startPoints = floatArrayOf(0.2f, 0.5f, 0.8f)
        val gradient = Gradient(colors, startPoints)

        val provider = HeatmapTileProvider.Builder()
            .data(locationList)
            .gradient(gradient)
            .opacity(0.30)
            .build()
        val overlay = map.addTileOverlay(TileOverlayOptions().tileProvider(provider))

        lifecycleScope.launch {
            delay(5000)
            overlay.clearTileCache()
            provider.setRadius(50)
        }
    }
}