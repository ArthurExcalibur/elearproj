package com.excalibur.enjoylearning.jetpack.ioc.hilt.example

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HiltEntityModule {

    @Provides
    @Singleton
    fun getHiltEntity() = HiltEntity()

}