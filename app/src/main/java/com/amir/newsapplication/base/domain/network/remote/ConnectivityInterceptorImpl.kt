package com.amir.newsapplication.base.domain.network.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.amir.newsapplication.R
import com.amir.newsapplication.utils.NetworkException
import com.amir.newsapplication.utils.string
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ConnectivityInterceptorImpl @Inject constructor(private val context: Context) :
    ConnectivityInterceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable(context)!!) {
            throw NetworkException(context.string(R.string.noInternetAvailable))
        } else
            return chain.proceed(chain.request())
    }

    private fun isInternetAvailable(context: Context): Boolean? {
        val connectivityManager: ConnectivityManager =
            context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
            when {
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
    }
}