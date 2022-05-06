package net.crosp.android.dagger2scopeinternals.di.component.application

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import net.crosp.android.dagger2scopeinternals.Dagger2ScopeInternalsApplication
import net.crosp.android.dagger2scopeinternals.di.component.activity.ActivityComponent
import net.crosp.android.dagger2scopeinternals.di.component.activity.module.ActivityModule
import net.crosp.android.dagger2scopeinternals.di.component.application.module.ApplicationScopedModule
import net.crosp.android.dagger2scopeinternals.di.component.application.module.ApplicationUnscopedModule
import net.crosp.android.dagger2scopeinternals.di.qualifiers.QualifierAppContext
import net.crosp.android.dagger2scopeinternals.di.scope.PerApplication

@[PerApplication Component(
    modules = [
        ApplicationUnscopedModule::class,
        ApplicationScopedModule::class
    ]
)]
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application,
            @[BindsInstance QualifierAppContext] context: Context
        ): ApplicationComponent
    }

    @QualifierAppContext
    fun appContext(): Context

    fun inject(mainApplication: Dagger2ScopeInternalsApplication)

    fun plusActivityComponent(activityModule: ActivityModule): ActivityComponent
}