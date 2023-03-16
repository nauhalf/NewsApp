package com.nauhalf.newsapp.core.wrapperresponse

import com.google.gson.annotations.SerializedName

data class RawHttpError(
    @SerializedName("status")
    val status: String?,
    @SerializedName("code")
    val code: String?,
    @SerializedName("message")
    val message: String?,
)
