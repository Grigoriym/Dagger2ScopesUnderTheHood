package net.crosp.android.dagger2scopeinternals.module.main.di.modules

import android.app.Activity
import dagger.Module
import dagger.Provides
import net.crosp.android.dagger2scopeinternals.di.scope.PerScreen
import net.crosp.android.dagger2scopeinternals.module.main.MainScreenDependency
import net.crosp.android.dagger2scopeinternals.module.main.MainScreenDependencyContract
import net.crosp.android.dagger2scopeinternals.ui.main.MainActivity

@Module
class MainScreenModule {

    @[Provides PerScreen]
    fun provideScreenActivity(containerActivity: Activity): MainActivity {
        return containerActivity as MainActivity
    }

    @[Provides PerScreen]
    fun provideMainScreenDependency(): MainScreenDependencyContract {
        return MainScreenDependency()
    }
}