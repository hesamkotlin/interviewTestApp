package com.example.interviewtestapp.ui

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.interviewtestapp.databinding.FragmentMapBinding
import com.example.interviewtestapp.ui.viewmodel.MapViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mbinding: FragmentMapBinding
    private lateinit var mGoogleMap: GoogleMap

    private val viewModel: MapViewModel by viewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mbinding = FragmentMapBinding.inflate(inflater, container, false)
        mbinding.viewmodel = viewModel
        mbinding.lifecycleOwner = this
        initMapView(savedInstanceState)
        return mbinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        getPermission()
    }

    private fun initMapView(savedInstanceState: Bundle?) {
        mbinding.mapView.onCreate(savedInstanceState)
        mbinding.mapView.onResume()
        try {
            MapsInitializer.initialize(requireActivity().applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        mbinding.mapView.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.setOnMapClickListener { setGoogleMapComponents(it) }
        mGoogleMap = googleMap
    }

    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val notGrantedPermissions = permissions.entries.filter { it.value == false }
            if (notGrantedPermissions.isEmpty()) {
                getLastLocation()
            } else {
                getPermission()
            }
        }

    private fun getPermission() {
        requestMultiplePermissions.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    @SuppressLint("MissingPermission")
    fun getLastLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                // Got last known location. In some rare situations this can be null.
                val latLng = if (location != null) {
                    LatLng(location.latitude, location.longitude)
                } else {
                    //if location service not available lanlng set on tehran by default
                    LatLng(35.68801713567976, 51.39160757066868)
                }
                setGoogleMapComponents(latLng)

            }
    }

    @SuppressLint("MissingPermission")
    private fun setGoogleMapComponents(latLng: LatLng) {
        viewModel.setLocation(latLng.latitude, latLng.longitude)
        mGoogleMap.isMyLocationEnabled = true
        mGoogleMap.clear()
        mGoogleMap.addMarker(
            MarkerOptions().position(latLng).title("my location")
        )

        val cameraPosition = CameraPosition.Builder().target(latLng).zoom(12f).build()
        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    override fun onResume() {
        super.onResume()
        mbinding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mbinding.mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mbinding.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mbinding.mapView.onLowMemory()
    }
}


