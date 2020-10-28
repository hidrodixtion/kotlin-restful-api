package com.projectbox.kotlinrestful.model

data class BaseResponse<T>(
        val code: Int,
        val status: String,
        val data: T
)