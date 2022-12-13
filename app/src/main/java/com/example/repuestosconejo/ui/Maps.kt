package com.example.repuestosconejo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.repuestosconejo.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions

class Maps : AppCompatActivity(),OnMapReadyCallback {
    private lateinit var map:GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_mapa)
        createFragment()
    }

    private fun createFragment() {
        val mapFragment=supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map=googleMap
        createMarker()
    }

    private fun createMarker() {
        val coordinates= com.google.android.gms.maps.model.LatLng(10.009041, -84.208153)
        val marker=MarkerOptions().position(coordinates).title("Repuestos Conejo")
        map.addMarker(marker)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates,18f),
            4000,
            null
        )
    }
}