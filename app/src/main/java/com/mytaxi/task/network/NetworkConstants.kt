package com.mytaxi.task.network

import com.mytaxi.task.BuildConfig

object NetworkConstants {

    val QA = BuildConfig.QA

    /**
     * The base url of the web service.
     */

    val BASE_URL = "https://fake-poi-api.mytaxi.com"

    //We use that for QA server for debugging - also we make favor for production and staging (check gradle)
    val BASE_URL_QA = "https://fake-poi-api.mytaxi.com"

    var BASE_URL_API = baseUrl + "/"

    val baseUrl: String
        get() = if (QA) {
            BASE_URL_QA
        } else {
            BASE_URL
        }
}