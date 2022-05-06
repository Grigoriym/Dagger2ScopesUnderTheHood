package net.crosp.android.dagger2scopeinternals.core.activity

import android.R
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import net.crosp.android.dagger2scopeinternals.core.contract.FragmentController.Multiple

abstract class BaseMultipleFragmentActivity : BaseActivity(), Multiple {

    protected var mCurrentFragmentClass: Class<*>? = null
    override fun popFragmentFromBackStack(@IdRes container: Int) {
        mFragmentManager!!.popBackStack()
    }

    /**
     * On create get fragment provided by child activities
     * adds it to the view hierarchy
     *
     * @param savedInstanceState bundle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * Replacing fragment by class
     *
     * @param fragmentClass  fragment class object
     * @param arguments      bundle for passing arguments
     * @param addToBackStack add fragment to backstack
     * @return success
     */
    override fun replaceFragment(
        fragmentClass: Class<*>,
        @IdRes container: Int,
        arguments: Bundle?,
        addToBackStack: Boolean,
        clearBackStack: Boolean
    ): Boolean {
        if (clearBackStack) {
            mFragmentManager?.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        if (!isInBackStack(fragmentClass, container)) {
            mCurrentFragmentClass = fragmentClass
            val fragmentTransaction = mFragmentManager!!.beginTransaction()
            fragmentTransaction.setCustomAnimations(
                R.anim.fade_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.fade_out
            )
            var fragment: Fragment? = null
            try {
                fragment = fragmentClass.newInstance() as Fragment
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
            assert(fragment != null)
            fragment!!.arguments = arguments
            fragmentTransaction.replace(container, fragment)
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(getFragmentClassKey(fragmentClass))
            }
            fragmentTransaction.commit()
            return true
        }
        return false
    }

    /**
     * Replace fragment without bundle
     *
     * @param fragmentClass  fragment class to be replaced with
     * @param addToBackStack add to back stack
     * @return success
     */
    override fun replaceFragment(
        fragmentClass: Class<*>,
        container: Int,
        addToBackStack: Boolean
    ): Boolean {
        return this.replaceFragment(fragmentClass, container, Bundle.EMPTY, addToBackStack, false)
    }

    /**
     * Replace fragment without bundle
     *
     * @param fragmentClass  fragment class to be replaced with
     * @param addToBackStack add to back stack
     * @return success
     */
    override fun replaceFragment(
        fragmentClass: Class<*>,
        container: Int,
        addToBackStack: Boolean,
        clearBackStack: Boolean
    ): Boolean {
        return this.replaceFragment(
            fragmentClass,
            container,
            Bundle.EMPTY,
            addToBackStack,
            clearBackStack
        )
    }

    /**
     * If fragment is already exists in back stack if so - don't replace it
     * and add to the back stack
     *
     * @param fragmentClass fragment class in order to compare with back stack entries
     * @return if fragment is already in back stack
     */
    fun isInBackStack(fragmentClass: Class<*>, container: Int): Boolean {
        val currentFragment = mFragmentManager!!.findFragmentById(container)
        if (currentFragment != null && getFragmentClassKey(currentFragment.javaClass) == getFragmentClassKey(
                fragmentClass
            )
        ) {
            return true
        }
        val count = mFragmentManager!!.backStackEntryCount
        if (count <= 0) {
            return false
        }
        for (i in 0 until count) {
            if (mFragmentManager!!.getBackStackEntryAt(i).name == getFragmentClassKey(fragmentClass)) {
                return true
            }
        }
        return false
    }

    /**
     * Get unique data which describes fragment
     *
     * @param fragmentClass fragment class
     * @return unique string for fragment class
     */
    protected fun getFragmentClassKey(fragmentClass: Class<*>): String {
        return fragmentClass.canonicalName
    }
}