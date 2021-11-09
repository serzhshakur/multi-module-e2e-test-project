package com.e2e.one

import com.e2e.one.api.ApiOperations
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS

@TestInstance(PER_CLASS)
@DisplayName("Some display name for sub module One")
class TestOne {

    private val apiOperations = ApiOperations()

    @Test
    fun `retrieving weather details by city`() {
        val weather = apiOperations.getWeather("Riga")
        assertThat(weather.currentTemp).isBetween(-30.0, 35.0)
    }
}
