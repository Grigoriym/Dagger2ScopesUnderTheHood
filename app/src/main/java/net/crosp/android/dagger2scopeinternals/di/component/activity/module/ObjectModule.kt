package net.crosp.android.dagger2scopeinternals.di.component.activity.module

import dagger.Module
import dagger.Provides
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.RepoForObjectModule
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.RepoForObjectModuleImpl
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.SomeClassForObjectModule
import net.crosp.android.dagger2scopeinternals.di.scope.PerActivity

@Module
object ObjectModule {

    @[Provides PerActivity]
    fun provideSomeClass(): SomeClassForObjectModule =
        SomeClassForObjectModule()

    @[Provides PerActivity]
    fun provideRepo(): RepoForObjectModule =
        RepoForObjectModuleImpl()
}