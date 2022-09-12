package com.mh.mhapp.data.network

import com.google.gson.GsonBuilder
import com.mh.mhapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Retrofit {

    private const val CONNECTION_TIMEOUT = 10L
    private const val READ_TIMEOUT = 30L
    private const val WRITE_TIMEOUT = 30L

    fun api(baseUrl: String): ApiService {
        /**
         * Se crea un by lazy para que se inicialice cuándo se necesite
         */
        val retrofit: Retrofit.Builder by lazy {
            val okhttpClient = OkHttpClient.Builder()
            okhttpClient.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            okhttpClient.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            okhttpClient.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)

            if (BuildConfig.BUILD_TYPE.contentEquals("debug")) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(Level.BODY)
                okhttpClient.addInterceptor(interceptor)
            }

            Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okhttpClient.build())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        }

        return retrofit.build().create(ApiService::class.java)
    }
}
