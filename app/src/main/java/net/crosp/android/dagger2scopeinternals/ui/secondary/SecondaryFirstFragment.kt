package net.crosp.android.dagger2scopeinternals.ui.secondary

import android.os.Bundle
import android.view.View
import android.widget.Button
import net.crosp.android.dagger2scopeinternals.R
import net.crosp.android.dagger2scopeinternals.core.fragment.BaseFragment
import net.crosp.android.dagger2scopeinternals.module.secondary.di.components.SecondaryScreenComponent
import javax.inject.Inject

class SecondaryFirstFragment : BaseFragment() {

    @JvmField
    @Inject
    var mRouter: SecondFragmentRouter? = null

    private lateinit var mSecondFragmentButton: Button
    private lateinit var activityButton: Button

    override fun getFragmentLayoutId(): Int {
        return R.layout.fragment_secondary_first
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getComponent(SecondaryScreenComponent::class.java)
            .plusMainFirstViewComponent()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mSecondFragmentButton = view.findViewById(R.id.button_second_fragment)
        activityButton = view.findViewById(R.id.button_main_activity)

        mSecondFragmentButton.setOnClickListener {
            mRouter?.onSwitchToSecondFragment()
        }
        activityButton.setOnClickListener {
            mRouter?.onSwitchBackToMainActivity()
        }
    }

    interface SecondFragmentRouter {
        fun onSwitchToSecondFragment()
        fun onSwitchBackToMainActivity()
    }
}