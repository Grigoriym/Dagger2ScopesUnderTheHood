package net.crosp.android.dagger2scopeinternals.di.component.application.module

import dagger.Module
import dagger.Provides
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.contract.CarDataRepositoryContract
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.implementation.CarDataRepository

@Module
class ApplicationUnscopedModule {

    @Provides
    fun provideCarDataRepository(): CarDataRepositoryContract {
        return CarDataRepository()
    }
}