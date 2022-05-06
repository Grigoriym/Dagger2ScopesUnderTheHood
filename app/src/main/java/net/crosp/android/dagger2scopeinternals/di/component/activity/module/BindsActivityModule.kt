package net.crosp.android.dagger2scopeinternals.di.component.activity.module

import dagger.Binds
import dagger.Module
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.Repo1
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.Repo1Impl

@Module
interface BindsActivityModule {

    @Binds
    fun provideRepo1(repo1Impl: Repo1Impl): Repo1

}