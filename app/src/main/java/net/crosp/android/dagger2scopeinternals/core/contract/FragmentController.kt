package net.crosp.android.dagger2scopeinternals.core.contract

import android.os.Bundle
import androidx.annotation.LayoutRes

/**
 * Created by Alexander Molochko on 9/3/15.
 * Project HeartHealthMonitor
 * Copyright (C) 2017 CROSP Solutions
 */
interface FragmentController {
    fun popFragmentFromBackStack()

    /**
     * Replace fragment with new one by class
     *
     * @param fragmentClass  fragment class object
     * @param arguments      bundle for passing arguments
     * @param addToBackStack add fragment to backstack
     * @return fragment was successfully replaced
     */
    fun replaceFragment(
        fragmentClass: Class<*>,
        arguments: Bundle?,
        addToBackStack: Boolean,
        clearBackStack: Boolean
    ): Boolean

    /**
     * Replace fragment with new one by class
     *
     * @param fragmentClass fragment class object
     * @return fragment was successfully replaced
     */
    fun replaceFragment(fragmentClass: Class<*>, arguments: Bundle): Boolean

    /**
     * Replace fragment without bundle
     *
     * @param fragmentClass  fragment class to be replaced with
     * @param addToBackStack add to back stack
     * @return fragment was successfully replaced
     */
    fun replaceFragment(fragmentClass: Class<*>, addToBackStack: Boolean): Boolean
    fun replaceFragment(
        fragmentClass: Class<*>?,
        arguments: Bundle?,
        addToBackStack: Boolean
    ): Boolean

    /**
     * Replace fragment and clear back stack
     *
     * @param fragmentClass  fragment class to be replaced with
     * @param addToBackStack add to back stack
     * @param clearBackStack clear previous back stack
     * @return success
     */
    fun replaceFragment(
        fragmentClass: Class<*>?,
        addToBackStack: Boolean,
        clearBackStack: Boolean
    ): Boolean

    interface Multiple {
        fun popFragmentFromBackStack(container: Int)
        fun replaceFragment(
            fragmentClass: Class<*>,
            @LayoutRes container: Int,
            arguments: Bundle?,
            addToBackStack: Boolean,
            clearBackStack: Boolean
        ): Boolean

        fun replaceFragment(
            fragmentClass: Class<*>,
            @LayoutRes container: Int,
            addToBackStack: Boolean
        ): Boolean

        fun replaceFragment(
            fragmentClass: Class<*>,
            @LayoutRes container: Int,
            addToBackStack: Boolean,
            clearBackStack: Boolean
        ): Boolean
    }
}