package com.rikaimind.appexercise.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)

data class User (
    @Json(name = "avatar_url")
    val avatar_url : String,
    @Json(name = "login")
    val login : String,
    @Json(name = "site_admin")
    val site_admin : Boolean,
)