package net.crosp.android.dagger2scopeinternals.module.main.di.modules

import android.app.Activity
import dagger.Module
import dagger.Provides
import net.crosp.android.dagger2scopeinternals.di.scope.PerFragment
import net.crosp.android.dagger2scopeinternals.ui.main.MainActivity
import net.crosp.android.dagger2scopeinternals.ui.main.MainFirstFragment

@Module
class MainFirstFragmentModule {

    @Provides
    @PerFragment
    fun provideSecondFragmentRouter(
        containerActivity: Activity
    ): MainFirstFragment.SecondFragmentRouter {
        return containerActivity as MainActivity
    }
}