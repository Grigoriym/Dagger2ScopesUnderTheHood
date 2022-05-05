package net.crosp.android.dagger2scopeinternals.module.main.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import net.crosp.android.dagger2scopeinternals.R
import net.crosp.android.dagger2scopeinternals.base.ui.fragment.BaseFragment
import net.crosp.android.dagger2scopeinternals.module.main.di.components.MainScreenComponent
import javax.inject.Inject

class MainSecondFragment : BaseFragment() {

    @JvmField
    @Inject
    var mRouter: FirstFragmentRouter? = null

    private lateinit var mFirstFragmentButton: Button
    private lateinit var activityButton: Button

    override fun getFragmentLayoutId(): Int {
        return R.layout.fragment_main_second
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getComponent(MainScreenComponent::class.java)
            .plusMainSecondViewComponent()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFirstFragmentButton = view.findViewById(R.id.button_first_fragment)
        activityButton = view.findViewById(R.id.button_secondary_activity)

        mFirstFragmentButton.setOnClickListener {
            mRouter?.onSwitchBackToFirstFragment()
        }
        activityButton.setOnClickListener {
            mRouter?.switchToSecondaryActivity()
        }
    }

    interface FirstFragmentRouter {
        fun onSwitchBackToFirstFragment()
        fun switchToSecondaryActivity()
    }
}