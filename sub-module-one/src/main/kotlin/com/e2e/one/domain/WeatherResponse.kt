package com.e2e.one.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class WeatherResponse(
    @field:JsonProperty("FeelsLikeC") val feelsLikeTemp: Double,
    @field:JsonProperty("temp_C") val currentTemp: Double,
    @field:JsonProperty("uvIndex") val uvIndex: Double,
)
