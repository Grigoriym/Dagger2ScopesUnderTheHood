package net.crosp.android.dagger2scopeinternals.core.contract

import androidx.appcompat.widget.Toolbar

/**
 * Created by Alexander Molochko on 1/9/17.
 * Project HeartHealthMonitor
 * Copyright (C) 2017 CROSP Solutions
 */
interface ToolbarController {
    val toolbar: Toolbar
    var toolbarTitle: String
    fun showToolbar()
    fun hideToolbar()
}