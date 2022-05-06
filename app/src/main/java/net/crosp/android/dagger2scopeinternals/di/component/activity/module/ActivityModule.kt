package net.crosp.android.dagger2scopeinternals.di.component.activity.module

import androidx.appcompat.app.AppCompatActivity
import net.crosp.android.dagger2scopeinternals.di.scope.PerActivity
import net.crosp.android.dagger2scopeinternals.di.component.activity.module.ActivityModule
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides
import net.crosp.android.dagger2scopeinternals.di.NamedConstants
import javax.inject.Named

@Module
class ActivityModule(private val mActivity: AppCompatActivity) {
    @Provides
    @PerActivity
    @Named(CONTEXT_ACTIVITY)
    fun provideActivityContext(): Context {
        return mActivity
    }

    /**
     * Provide the activity to dependents in the graph.
     */
    @Provides
    @PerActivity
    fun provideActivity(): Activity {
        return mActivity
    }

    /**
     * Provide the layout inflater to dependents in the graph.
     */
    @Provides
    @PerActivity
    fun provideLayoutInflater(): LayoutInflater {
        return LayoutInflater.from(mActivity)
    }

    /**
     * Provide the layout inflater to dependents in the graph.
     */
    @Provides
    @Named(NamedConstants.Fragment.SUPPORT_FRAGMENT_MANAGER)
    @PerActivity
    fun provideSupportFragmentManager(): FragmentManager {
        return mActivity.supportFragmentManager
    }

    @Provides
    @Named(NamedConstants.Fragment.DEFAULT_FRAGMENT_MANAGER)
    @PerActivity
    fun provideFragmentManager(): FragmentManager {
        return mActivity.supportFragmentManager
    }

    companion object {
        const val CONTEXT_ACTIVITY = "context.activity"
    }
}