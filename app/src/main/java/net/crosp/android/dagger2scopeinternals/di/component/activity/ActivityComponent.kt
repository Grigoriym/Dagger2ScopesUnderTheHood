package net.crosp.android.dagger2scopeinternals.di.component.activity

import dagger.Subcomponent
import net.crosp.android.dagger2scopeinternals.core.activity.BaseActivity
import net.crosp.android.dagger2scopeinternals.di.component.activity.module.ActivityModule
import net.crosp.android.dagger2scopeinternals.di.scope.PerActivity
import net.crosp.android.dagger2scopeinternals.module.main.di.components.MainScreenComponent
import net.crosp.android.dagger2scopeinternals.module.secondary.di.components.SecondaryScreenComponent

@[PerActivity Subcomponent(
    modules = [
        ActivityModule::class
    ]
)]
interface ActivityComponent {

    fun inject(baseActivity: BaseActivity)

    fun plusMainScreenComponent(): MainScreenComponent?
    fun plusSecondaryScreenComponent(): SecondaryScreenComponent?
}