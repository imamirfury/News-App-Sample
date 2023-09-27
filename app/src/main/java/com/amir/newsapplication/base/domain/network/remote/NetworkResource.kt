package com.amir.newsapplication.base.domain.network.remote

enum class Status {
    SUCCESS, ERROR
}

data class NetworkResource<T>(val status: Status, val data: T?, val error: String?) {
    companion object {
        fun <T> success(data: T?): NetworkResource<T> {
            return NetworkResource(Status.SUCCESS, data, null)
        }

        fun <T> error(error: String?): NetworkResource<T> {
            return NetworkResource(Status.ERROR, null, error)
        }
    }
}