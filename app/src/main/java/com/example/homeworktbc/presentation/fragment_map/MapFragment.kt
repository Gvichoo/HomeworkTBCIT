package com.example.homeworktbc.presentation.fragment_map

import android.Manifest
import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import android.provider.Settings
import androidx.core.content.ContextCompat
import com.example.homeworktbc.R
import com.example.homeworktbc.data.api.Location
import com.example.homeworktbc.databinding.FragmentMap2Binding
import com.example.homeworktbc.domain.LocationRepository
import com.example.homeworktbc.presentation.base.BaseFragment
import com.example.homeworktbc.presentation.bottom_sheet.LocationBottomSheetFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMap2Binding>(FragmentMap2Binding::inflate), OnMapReadyCallback {

    private var googleMap: GoogleMap? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val locationManager: LocationManager by lazy {
        requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    @Inject
    lateinit var locationRepository: LocationRepository

    private val locationReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (isLocationEnabled()) {
                checkPermissionsAndFetchLocation()
            }
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                checkLocationEnabledAndFetch()
            } else {
                showError("Location permission denied.")
            }
        }

    override fun start() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        val filter = IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION)
        requireContext().registerReceiver(locationReceiver, filter)

        checkLocationEnabledAndFetch()
    }

    override fun onStop() {
        super.onStop()
        requireContext().unregisterReceiver(locationReceiver)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        checkLocationEnabledAndFetch()

        googleMap?.setOnMarkerClickListener { marker ->
            val location = marker.tag as? Location
            location?.let {
                showBottomSheet(it)
            }
            true
        }
    }

    private fun checkLocationEnabledAndFetch() {
        if (isLocationEnabled()) {
            checkPermissionsAndFetchLocation()
        } else {
            showLocationEnableDialog()
        }
    }

    private fun showLocationEnableDialog() {
        AlertDialog.Builder(requireContext())
            .setMessage("Location services are disabled. Please enable them in settings.")
            .setCancelable(false)
            .setPositiveButton("Enable") { _, _ ->
                startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun isLocationEnabled(): Boolean {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun checkPermissionsAndFetchLocation() {
        when {
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
                getDeviceLocation()
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    private fun getDeviceLocation() {
        if (ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000).apply {
                setMinUpdateDistanceMeters(10f)
                setMaxUpdates(1)
            }

            fusedLocationClient.requestLocationUpdates(locationRequest.build(), object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    super.onLocationResult(locationResult)
                    val location = locationResult.lastLocation
                    if (location != null) {
                        val currentLatLng = LatLng(location.latitude, location.longitude)
                        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
                        googleMap?.addMarker(MarkerOptions().position(currentLatLng).title("You are here"))
                        Log.d("Location", "Latitude: ${location.latitude}, Longitude: ${location.longitude}")
                        fetchLocations()
                    } else {
                        showError("Unable to fetch your location.")
                    }
                }
            }, Looper.getMainLooper())
        } else {
            showError("Location permission not granted.")
        }
    }

    private fun addMarkersToMap(locations: List<Location>) {
        for (location in locations) {
            val latLng = LatLng(location.lat, location.lan)
            val marker = googleMap?.addMarker(
                MarkerOptions().position(latLng).title(location.title)
            )

            marker?.tag = location
        }
    }

    private fun fetchLocations() {
        locationRepository.getLocations().enqueue(object : Callback<List<Location>> {
            override fun onResponse(call: Call<List<Location>>, response: Response<List<Location>>) {
                if (response.isSuccessful) {
                    val locations = response.body()
                    if (locations != null) {
                        addMarkersToMap(locations)
                    } else {
                        showError("No locations found.")
                    }
                } else {
                    showError("Failed to fetch locations: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Location>>, t: Throwable) {
                showError("Error fetching locations: ${t.message}")
            }
        })
    }

    private fun showBottomSheet(location: Location) {
        val bottomSheetFragment = LocationBottomSheetFragment(location)
        bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
