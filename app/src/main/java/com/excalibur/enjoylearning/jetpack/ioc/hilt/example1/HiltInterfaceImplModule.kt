package com.excalibur.enjoylearning.jetpack.ioc.hilt.example1

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class HiltInterfaceImplModule {

    @Binds
    abstract fun bindInterface(hiltInterfaceImpl: HiltInterfaceImpl) : HiltInterface

}