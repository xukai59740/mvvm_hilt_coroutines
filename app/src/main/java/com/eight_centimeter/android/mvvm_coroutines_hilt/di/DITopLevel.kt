package com.eight_centimeter.android.mvvm_coroutines_hilt.di

import com.eight_centimeter.android.mvvm_coroutines_hilt.BuildConfig
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.account.AccountApi
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.account.AccountRepository
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.common.CommonParameterInterceptor
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.common.SharedPrefs
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.main.MainViewModel

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val sourceModule = module {
    single {
        // interceptors
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

        //okHttpClient
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.readTimeout(1, TimeUnit.MINUTES)
        clientBuilder.connectTimeout(1, TimeUnit.MINUTES)
        interceptors.forEach { clientBuilder.addInterceptor(it) }
        val okHttpClient = clientBuilder.build()


        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    single(named("Mock")) {
        // interceptors
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

        //okHttpClient
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.readTimeout(1, TimeUnit.MINUTES)
        clientBuilder.connectTimeout(1, TimeUnit.MINUTES)
        interceptors.forEach { clientBuilder.addInterceptor(it) }
        val okHttpClient = clientBuilder.build()


        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://baidu.ai:8081")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    single {
        SharedPrefs(androidApplication())
    }
}


private val repositoryModule = module {

    //Employer
    single {
        get<Retrofit>(named("Mock")).create(AccountApi::class.java)
    }
    single { AccountRepository(get(),get()) } bind AccountRepository::class

}

private val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}

val appModule = listOf(sourceModule, repositoryModule, viewModelModule)
