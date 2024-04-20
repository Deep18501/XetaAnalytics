package com.example.xeta.data.model

data class ResultApi<T>(
    val data: T?,
    val message: String,
    val success: Boolean
)