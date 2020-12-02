package com.eight_centimeter.android.mvvm_coroutines_hilt.di

import com.eight_centimeter.android.hiltdemo.di.MockRetrofit
import com.eight_centimeter.android.hiltdemo.di.NetworkModule
import com.eight_centimeter.android.hiltdemo.di.NormalRetrofit
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.account.AccountApi
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.account.AccountRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(ApplicationComponent::class)
class AccountModule {

    @Provides
    @MockAccountApi
    fun provideMockAccountApi(@MockRetrofit retrofit: Retrofit): AccountApi {
        return retrofit.create(AccountApi::class.java)
    }

    @Provides
    @NormalAccountApi
    fun provideAccountApi(@NormalRetrofit retrofit: Retrofit): AccountApi {
        return retrofit.create(AccountApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAccountRepository(
        @NormalAccountApi accountApi: AccountApi
    ): AccountRepository {
        return AccountRepository(accountApi)
    }
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class NormalAccountApi

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MockAccountApi


