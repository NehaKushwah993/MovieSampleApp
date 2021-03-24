package com.nehak.moviesampleapp.dependencyInjection

import com.nehak.moviesampleapp.BuildConfig
import com.nehak.moviesampleapp.backend.remote.RemoteConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Neha Kushwah on 3/24/21.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val baseUrl = RemoteConfig.BASE_URL

    @Provides
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor;
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(object : Interceptor {

                override fun intercept(chain: Interceptor.Chain): Response {
                    val original = chain.request()
                    val httpUrl = original.url.newBuilder()
                        .addQueryParameter("api_key", BuildConfig.API_KEY)
                        .build()

                    val requestBuilder: Request.Builder = original.newBuilder()
                        .url(httpUrl)

                    return chain.proceed(requestBuilder.build())
                }
            })
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }


}