package com.e2e.two

import com.e2e.one.api.ApiOperations
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS

@TestInstance(PER_CLASS)
@DisplayName("Some display name for sub module Two")
class TestTwo {

    private val apiOperations = ApiOperations()

    @Test
    fun `checking UV index`() {
        val weather = apiOperations.getWeather("Riga")
        assertThat(weather.uvIndex).isGreaterThan(0.0)
    }
}
