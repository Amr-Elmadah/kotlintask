package com.mytaxi.task.network

import com.mytaxi.task.BuildConfig

object NetworkConstants {

    private const val QA = BuildConfig.QA

    /**
     * The base url of the web service.
     */

    private const val BASE_URL = "https://fake-poi-api.mytaxi.com"

    //We use that for QA server for debugging - also we make favor for production and staging (check gradle)
    private const val BASE_URL_QA = "https://fake-poi-api.mytaxi.com"

    var BASE_URL_API = baseUrl

    private val baseUrl: String
        get() = if (QA) {
            BASE_URL_QA
        } else {
            BASE_URL
        }
}