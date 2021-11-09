package com.e2e.one.api

import com.e2e.one.config.allureRestAssuredFilter
import com.e2e.one.config.objectMapper
import com.e2e.one.domain.WeatherResponse
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.config.EncoderConfig.encoderConfig
import io.restassured.config.JsonConfig.jsonConfig
import io.restassured.config.LogConfig.logConfig
import io.restassured.config.ObjectMapperConfig
import io.restassured.config.RestAssuredConfig
import io.restassured.http.ContentType.JSON
import io.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL
import io.restassured.specification.RequestSpecification

val restAssuredConfig: RestAssuredConfig by lazy {
    val objectMapperConfig = ObjectMapperConfig()
        .jackson2ObjectMapperFactory { _, _ -> objectMapper }

    val encoderConfig = encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)

    RestAssuredConfig.newConfig()
        .with().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL))
        .with().logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails())
        .with().objectMapperConfig(objectMapperConfig)
        .with().encoderConfig(encoderConfig)
}

val reqSpec: RequestSpecification = RequestSpecBuilder()
    .setBaseUri("https://wttr.in/")
    .addQueryParam("format", "j1")
    .setConfig(restAssuredConfig)
    .addFilter(allureRestAssuredFilter)
    .setContentType(JSON)
    .setAccept("application/json")
    .build()

class ApiOperations {

    private fun weather(): RequestSpecification = RestAssured.given().spec(reqSpec)

    fun getWeather(city: String): WeatherResponse = weather()
        .get("/$city")
        .then().statusCode(200)
        .extract().jsonPath()
        .getObject("current_condition.first()", WeatherResponse::class.java)
}
