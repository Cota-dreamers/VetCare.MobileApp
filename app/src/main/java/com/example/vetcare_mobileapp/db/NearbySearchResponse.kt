package com.example.vetcare_mobileapp.db

data class NearbySearchResponse(
    val results: List<PlaceResult> = emptyList()
)

data class PlaceResult(
    val name: String = "",
    val geometry: Geometry
)

data class Geometry(
    val location: Location
)

data class Location(
    val lat: Double = 0.0,
    val lng: Double = 0.0
)
