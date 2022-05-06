package net.crosp.android.dagger2scopeinternals.di.component.application.module

import android.app.Application
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.contract.CarDataRepositoryContract
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.contract.GlobalEventNotifierContract
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.implementation.CarDataRepository
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.implementation.SomeEvent
import net.crosp.android.dagger2scopeinternals.di.NamedConstants
import net.crosp.android.dagger2scopeinternals.di.qualifiers.QualifierScopeRepo
import net.crosp.android.dagger2scopeinternals.di.scope.PerApplication
import javax.inject.Named

@Module
class ApplicationScopedModule {

    @Provides
    @PerApplication
    fun provideResources(
        application: Application
    ): Resources {
        return application.resources
    }

    @Provides
    @PerApplication
    fun provideEventNotifier(
        application: Application
    ): GlobalEventNotifierContract<SomeEvent> {
        return application as GlobalEventNotifierContract<SomeEvent>
    }

    @Provides
    @PerApplication
    @Named(NamedConstants.Environment.SANDBOX_PATH)
    fun provideSandboxPath(
        application: Application
    ): String {
        return application.applicationInfo.dataDir
    }

    @Provides
    @PerApplication
    @QualifierScopeRepo
    fun provideScopedRepo(): CarDataRepositoryContract {
        return CarDataRepository()
    }
}