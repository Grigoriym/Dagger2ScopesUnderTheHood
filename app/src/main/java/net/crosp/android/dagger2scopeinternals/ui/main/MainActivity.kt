package net.crosp.android.dagger2scopeinternals.ui.main

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import net.crosp.android.dagger2scopeinternals.R
import net.crosp.android.dagger2scopeinternals.core.activity.BaseSingleFragmentActivity
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.contract.CarDataRepositoryContract
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.contract.GlobalEventNotifierContract
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.implementation.SomeEvent
import net.crosp.android.dagger2scopeinternals.di.contract.ProvidesComponent
import net.crosp.android.dagger2scopeinternals.di.qualifiers.QualifierScopeRepo
import net.crosp.android.dagger2scopeinternals.module.main.MainScreenDependencyContract
import net.crosp.android.dagger2scopeinternals.module.main.di.components.MainScreenComponent
import net.crosp.android.dagger2scopeinternals.ui.secondary.SecondaryActivity
import javax.inject.Inject

class MainActivity : BaseSingleFragmentActivity(),
    ProvidesComponent<MainScreenComponent>,
    MainFirstFragment.SecondFragmentRouter,
    MainSecondFragment.FirstFragmentRouter,
    GlobalEventNotifierContract.EventListener<SomeEvent> {

    @Inject
    lateinit var mCarRepoOther: CarDataRepositoryContract

    @[Inject QualifierScopeRepo]
    lateinit var scopedRepo: CarDataRepositoryContract

    @Inject
    lateinit var mMainScreenDependency: MainScreenDependencyContract

    @Inject
    lateinit var mGlobalEventNotifier: GlobalEventNotifierContract<SomeEvent>

    override val mainLayoutId: Int
        get() = R.layout.activity_main

    override val contentLayoutId: Int
        get() = R.layout.content_toolbar_single_fragment

    override val contentContainerId: Int
        get() = R.id.linear_layout_content

    var mMainToolbar: Toolbar? = null
    var mActionBar: ActionBar? = null
    var mMainScreenComponent: MainScreenComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
        initUI()
        navigateToInitialScreen()

        println("asd MainActivity unscoped $mCarRepoOther")
        println("asd MainActivity scoped $scopedRepo")
    }

    private fun setEventListeners() {
        mGlobalEventNotifier!!.addListener(this)
    }

    private fun injectDependencies() {
        mMainScreenComponent = mActivityComponent.plusMainScreenComponent()
        mMainScreenComponent!!.inject(this)
    }

    override fun onResume() {
        super.onResume()
    }

    private fun initUI() {
        initToolbar()
    }

    fun navigateToInitialScreen() {
        replaceFragment(MainFirstFragment::class.java, false)
    }

    protected fun initToolbar() {
        mMainToolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(mMainToolbar)
        mActionBar = supportActionBar
    }

    override fun getComponent(): MainScreenComponent {
        return mMainScreenComponent!!
    }


    override fun getFragmentContainerLayoutId(): Int {
        return R.id.frame_layout_fragment_container
    }

    override fun onSwitchToSecondFragment() {
        replaceFragment(MainSecondFragment::class.java, false)
    }

    override fun onSwitchBackToFirstFragment() {
        replaceFragment(MainFirstFragment::class.java, false)
    }

    override fun switchToSecondaryActivity() {
        startNewActivity(SecondaryActivity::class.java)
    }

    override fun onEvent(event: SomeEvent) {
        //
    }
}