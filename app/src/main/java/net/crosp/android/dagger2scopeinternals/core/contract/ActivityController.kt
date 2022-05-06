package net.crosp.android.dagger2scopeinternals.core.contract

import android.os.Bundle

/**
 * Created by Alexander Molochko on 1/10/17.
 * Project HeartHealthMonitor
 * Copyright (C) 2017 CROSP Solutions
 */
interface ActivityController {
    /**
     * Start new activity method in order to start activities from
     * fragments without tight coupling
     *
     * @param activityClass activity class
     */
    fun startNewActivity(activityClass: Class<*>)
    fun startNewActivityCleaBackStack(activityClass: Class<*>)
    fun startNewActivityCleaBackStack(activityClass: Class<*>, bundle: Bundle?)

    /**
     * The same but with bundle
     *
     * @param activityClass activity class
     * @param bundle        bundle object
     */
    fun startNewActivity(activityClass: Class<*>, bundle: Bundle?)

    /**
     * Start new activity for result
     *
     * @param activityClass activity class
     * @param requestCode   request code
     */
    fun startNewActivityForResult(activityClass: Class<*>, requestCode: Int)

    /**
     * Start new activity for result with bundle
     *
     * @param activityClass activity class
     * @param bundle        bundle
     * @param requestCode   request code
     */
    fun startNewActivityForResult(activityClass: Class<*>?, bundle: Bundle?, requestCode: Int)

    /**
     * Start new activity in a new task
     *
     * @param activityClass activity class
     */
    fun startNewActivityNewTask(activityClass: Class<*>?)

    /**
     * Start new activity with clearing top activities
     *
     * @param activityClass activity class
     */
    fun startNewActivityClearTop(activityClass: Class<*>?)

    /**
     * Start new activity if not a top activity
     *
     * @param activityClass activity class
     */
    fun startNewActivitySingleTop(activityClass: Class<*>?)
}