package net.crosp.android.dagger2scopeinternals.module.main.di.components

import net.crosp.android.dagger2scopeinternals.di.scope.PerFragment
import dagger.Subcomponent
import net.crosp.android.dagger2scopeinternals.module.main.di.modules.MainFirstFragmentModule
import net.crosp.android.dagger2scopeinternals.ui.main.MainFirstFragment

@PerFragment
@Subcomponent(modules = [MainFirstFragmentModule::class])
interface MainFirstViewComponent {
    fun inject(fragment: MainFirstFragment)
}