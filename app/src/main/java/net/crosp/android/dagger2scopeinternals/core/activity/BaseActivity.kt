package net.crosp.android.dagger2scopeinternals.core.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import net.crosp.android.dagger2scopeinternals.core.contract.ActivityController
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.contract.CarDataRepositoryContract
import net.crosp.android.dagger2scopeinternals.di.NamedConstants
import net.crosp.android.dagger2scopeinternals.di.component.activity.ActivityComponent
import net.crosp.android.dagger2scopeinternals.di.component.activity.module.ActivityModule
import net.crosp.android.dagger2scopeinternals.di.component.application.ApplicationComponent
import net.crosp.android.dagger2scopeinternals.di.contract.ProvidesComponent
import javax.inject.Inject
import javax.inject.Named

abstract class BaseActivity : AppCompatActivity(), ActivityController {

    @Inject
    lateinit var mCarRepo: CarDataRepositoryContract

    protected var mContentContainer: ViewGroup? = null

    @Inject
    lateinit var mLayoutInflater: LayoutInflater

    @JvmField
    @Inject
    @Named(NamedConstants.Fragment.SUPPORT_FRAGMENT_MANAGER)
    var mFragmentManager: FragmentManager? = null

    protected lateinit var mActivityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDependencyComponents()
        mFragmentManager = supportFragmentManager
        mLayoutInflater = LayoutInflater.from(this)
        beforeSetContent()
        setContentView(mainLayoutId)
        afterSetContent()
        mContentContainer = findViewById<View>(contentContainerId) as ViewGroup
        if (mContentContainer != null) {
            mLayoutInflater.inflate(contentLayoutId, mContentContainer, true)
        }
    }

    private fun initDependencyComponents() {
        val appComponent = (application as ProvidesComponent<ApplicationComponent>).component
        mActivityComponent = appComponent.plusActivityComponent(ActivityModule(this))
    }

    /*    @Override
    public ActivityComponent getComponent() {
        if (mActivityComponent == null) {
            initDependencyComponents();
        }
        return mActivityComponent;
    }*/
    //=======================A=========================================================
    // Hooks for executing actions during execution of activity lifecycle methods
    //================================================================================
    fun beforeSetContent() {}
    fun afterSetContent() {}
    //================================================================================
    // Template methods to be overridden by child classes
    // Get layout id in order to inflate it and add to the root view
    //================================================================================
    /**
     * Provide main layout id, layout resource used for setting content view
     *
     * @return main layout id @LayoutRes
     */
    @get:LayoutRes
    abstract val mainLayoutId: Int

    /**
     * Lauout id inside a layout provided by the  [.getMainLayoutId] method.
     *
     * @return
     */
    @get:IdRes
    abstract val contentContainerId: Int

    /**
     * Provide main content layout
     * This layout is inflated inside a layout provided by the [.getContentContainerId] method
     *
     * @return id of main content layout, where main screen content will be put
     */
    @get:LayoutRes
    abstract val contentLayoutId: Int

    //================================================================================
    // Public methods
    //================================================================================
    fun provideLayoutInflater(): LayoutInflater? {
        return mLayoutInflater
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    fun navigateBackForce() {
        super.onBackPressed()
    }

    fun getStringByResourceId(stringId: Int): String {
        return getString(stringId)
    }

    //================================================================================
    // ActivityController implementation
    //================================================================================
    override fun startNewActivity(activityClass: Class<*>) {
        startActivity(Intent(this, activityClass))
    }

    override fun startNewActivityNewTask(activityClass: Class<*>?) {
        val intent = Intent(this, activityClass)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun startNewActivityCleaBackStack(activityClass: Class<*>) {
        startNewActivityCleaBackStack(activityClass, null)
    }

    override fun startNewActivityCleaBackStack(activityClass: Class<*>, bundle: Bundle?) {
        val intent = Intent(this, activityClass)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    override fun startNewActivity(activityClass: Class<*>, bundle: Bundle?) {
        startActivity(Intent(this, activityClass).apply {
            bundle?.let {
                putExtras(bundle)
            }
        })
    }

    override fun startNewActivityForResult(activityClass: Class<*>, requestCode: Int) {
        val intent = Intent(this, activityClass)
        startActivityForResult(intent, requestCode)
    }

    override fun startNewActivityForResult(
        activityClass: Class<*>?,
        bundle: Bundle?,
        requestCode: Int
    ) {
        val intent = Intent(this, activityClass)
        intent.putExtras(bundle!!)
        startActivityForResult(intent, requestCode)
    }

    override fun startNewActivityClearTop(activityClass: Class<*>?) {
        val intent = Intent(this, activityClass)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    override fun startNewActivitySingleTop(activityClass: Class<*>?) {
        val intent = Intent(this, activityClass)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
    }
}