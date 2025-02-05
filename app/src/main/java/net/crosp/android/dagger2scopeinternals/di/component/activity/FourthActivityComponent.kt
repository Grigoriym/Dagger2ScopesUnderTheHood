package net.crosp.android.dagger2scopeinternals.di.component.activity

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import net.crosp.android.dagger2scopeinternals.di.component.activity.module.BindsActivityModule
import net.crosp.android.dagger2scopeinternals.di.component.activity.module.NewActivityModule
import net.crosp.android.dagger2scopeinternals.di.component.application.ApplicationComponent
import net.crosp.android.dagger2scopeinternals.di.qualifiers.QualifierActivityContext
import net.crosp.android.dagger2scopeinternals.di.scope.PerActivity
import net.crosp.android.dagger2scopeinternals.ui.third.FourthActivity

@[PerActivity Component(
    modules = [
        NewActivityModule::class,
        BindsActivityModule::class
    ],
    dependencies = [
        ApplicationComponent::class
    ]
)]
interface FourthActivityComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @[BindsInstance QualifierActivityContext] context: Context,
            applicationComponent: ApplicationComponent
        ): FourthActivityComponent
    }

    fun inject(fourthActivity: FourthActivity)
}