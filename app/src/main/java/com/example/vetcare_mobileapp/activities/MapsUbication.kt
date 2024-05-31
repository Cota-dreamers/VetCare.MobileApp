package com.example.vetcare_mobileapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.vetcare_mobileapp.R
import com.example.vetcare_mobileapp.db.NearbySearchResponse
import com.example.vetcare_mobileapp.network.GooglePlacesApiService
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MapsUbication : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map:GoogleMap
    private lateinit var placesClient: PlacesClient
    private lateinit var googplePlacesApiService: GooglePlacesApiService

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        Places.initialize(applicationContext, getString(R.string.google_maps_key))
        placesClient = Places.createClient(this)

        createFragment()
        setupRetrofit()
    }

    private fun createFragment(){
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun setupRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://maps.googleapis.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        googplePlacesApiService = retrofit.create(GooglePlacesApiService::class.java)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        createMarker()
        /*
        val lima = LatLng(-12.0464,-77.0428)
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(lima,12f))
        findNearbyVeterinarias(lima)
         */
    }

    private fun createMarker() {
        val coordinates = LatLng(-11.997762,-77.052608)
        val marker = MarkerOptions().position(coordinates).title("Veterinaria cerca!")
        map.addMarker(marker)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates,18f),
            4000,
            null
        )
    }

    /*private fun findNearbyVeterinarias(location: LatLng) {

        val locationStr = "${location.latitude},${location.longitude}"
        val radius = 5000
        val type = "veterinary_care"
        val apiKey = getString(R.string.google_maps_key)

        googplePlacesApiService.getNearbyPlaces(locationStr, radius, type, apiKey)
            .enqueue(object : Callback<NearbySearchResponse> {
                override fun onResponse(call: Call<NearbySearchResponse>, response: Response<NearbySearchResponse>){
                    if(response.isSuccessful){
                        response.body()?.results?.forEach{place ->
                            val latLng = LatLng(place.geometry.location.lat, place.geometry.location.lng)
                            map.addMarker(MarkerOptions().position(latLng).title(place.name))
                        }
                    }
                }

                override fun onFailure(call: Call<NearbySearchResponse>, t: Throwable){
                    t.printStackTrace()
                }
            })
    }*/


}