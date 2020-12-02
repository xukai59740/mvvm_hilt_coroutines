package com.eight_centimeter.android.hiltdemo.di

import android.content.Context
import android.util.Log
import com.eight_centimeter.android.mvvm_coroutines_hilt.BuildConfig
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.common.CommonParameterInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory.create
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    @NormalRetrofit
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(create())
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @MockRetrofit
    fun provideMockRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(create())
            .baseUrl("https://mock.search.com")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideOkHttpClient(interceptors: ArrayList<Interceptor>): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.readTimeout(1, TimeUnit.MINUTES)
        clientBuilder.connectTimeout(1, TimeUnit.MINUTES)
        interceptors.forEach { clientBuilder.addInterceptor(it) }
        return clientBuilder.build()
    }

    @Provides
    fun provideInterceptors(@ApplicationContext context: Context): ArrayList<Interceptor> {
        val interceptors = arrayListOf<Interceptor>()
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
        interceptors.add(CommonParameterInterceptor())
        interceptors.add(loggingInterceptor)
        return interceptors
    }
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class NormalRetrofit

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MockRetrofit
