package com.example.vetcare_mobileapp.communication

import com.example.vetcare_mobileapp.models.CareAdvice
import com.google.gson.annotations.SerializedName

data class ApiResponse (
    @SerializedName("careadvices")
    val careadvices: List<CareAdvice>

)