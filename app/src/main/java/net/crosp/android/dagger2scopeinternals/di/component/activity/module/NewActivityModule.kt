package net.crosp.android.dagger2scopeinternals.di.component.activity.module

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.*
import net.crosp.android.dagger2scopeinternals.di.qualifiers.QualifierAppContext
import net.crosp.android.dagger2scopeinternals.di.scope.PerActivity

@Module
class NewActivityModule {

    @Provides
    @PerActivity
    fun provideActivityRepo(
        @QualifierAppContext context: Context
    ): ActivityScopedRepo =
        ActivityScopedRepo()

    @Provides
    @PerActivity
    fun provideRepo2(): Repo2 = Repo2Impl()
}