package com.eight_centimeter.android.mvvm_coroutines_hilt.di

import android.content.Context
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.common.SharedPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
class SharePrefModule {

    @Provides
    fun provideSharedPref(@ApplicationContext context: Context): SharedPrefs {
        return SharedPrefs(context)
    }
}
