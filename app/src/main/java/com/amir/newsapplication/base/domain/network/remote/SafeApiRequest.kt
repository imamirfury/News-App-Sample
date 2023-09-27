package com.amir.newsapplication.base.domain.network.remote

import retrofit2.Response

abstract class SafeApiRequest {
    suspend fun <T> apiCall(call: suspend () -> Response<T>): NetworkResource<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                NetworkResource.success(response.body())
            } else {
                NetworkResource.error(response.code().toString())
            }
        } catch (e: Exception) {
            return NetworkResource.error(e.localizedMessage)
        }
    }
}