package com.example.vetcare_mobileapp.communication

import com.example.vetcare_mobileapp.models.CareAdvice

class CareAdviceResponse(

    private var strAdvice: String,
    private var strAdviceThumb: String,
    private var strAdviceCategory: String,
    private var strAdviceDescription: String,
) {
    fun toCareAdvice(): CareAdvice {
        return CareAdvice(
            strAdvice = strAdvice,
            strAdviceThumb = strAdviceThumb,
            strAdviceCategory = strAdviceCategory,
            strAdviceDescription = strAdviceDescription
        )
    }
}