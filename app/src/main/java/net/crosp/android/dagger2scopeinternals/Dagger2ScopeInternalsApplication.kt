package net.crosp.android.dagger2scopeinternals

import android.app.Application
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.contract.CarDataRepositoryContract
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.contract.GlobalEventNotifierContract
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.implementation.SomeEvent
import net.crosp.android.dagger2scopeinternals.di.component.application.ApplicationComponent
import net.crosp.android.dagger2scopeinternals.di.component.application.DaggerApplicationComponent
import net.crosp.android.dagger2scopeinternals.di.contract.ProvidesComponent
import net.crosp.android.dagger2scopeinternals.di.qualifiers.QualifierScopeRepo
import javax.inject.Inject

class Dagger2ScopeInternalsApplication : Application(), ProvidesComponent<ApplicationComponent>,
    GlobalEventNotifierContract<SomeEvent> {

    private val mEventListeners: MutableList<GlobalEventNotifierContract.EventListener<SomeEvent>> =
        ArrayList()

    @Inject
    lateinit var mCarRepo: CarDataRepositoryContract

    @[Inject QualifierScopeRepo]
    lateinit var scopedRepo: CarDataRepositoryContract

    private val mApplicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory()
            .create(this, this)
    }

    override fun onCreate() {
        super.onCreate()
        mApplicationComponent.inject(this)
        println("asd App unscopedRepo $mCarRepo")
        println("asd App scopedRepo: $scopedRepo")
    }

    override fun getComponent(): ApplicationComponent {
        return mApplicationComponent
    }

    override fun addListener(eventListener: GlobalEventNotifierContract.EventListener<SomeEvent>) {
        mEventListeners.add(eventListener)
    }

}