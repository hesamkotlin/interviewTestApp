package com.example.interviewtestapp.ui

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import com.example.interviewtestapp.databinding.FragmentMapBinding
import com.example.interviewtestapp.domain.UserRepository
import com.example.interviewtestapp.ui.viewmodel.MapViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MapFragment : Fragment() {
    private lateinit var mbinding: FragmentMapBinding

    private val viewModel: MapViewModel by viewModels()

    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mbinding = FragmentMapBinding.inflate(inflater, container, false)
        mbinding.viewmodel = viewModel
        mbinding.lifecycleOwner=this
        return mbinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPermission()
    }

    private fun getPermission() {
        requestMultiplePermissions.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private val requestMultiplePermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        val notGrantedPermissions = permissions.entries.filter { it.value == false }
        if (notGrantedPermissions.isEmpty()){
            // get current location
        }else {
            getPermission()
        }
    }
}


