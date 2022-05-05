package net.crosp.android.dagger2scopeinternals.module.main.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import net.crosp.android.dagger2scopeinternals.R
import net.crosp.android.dagger2scopeinternals.base.ui.fragment.BaseFragment
import net.crosp.android.dagger2scopeinternals.module.main.di.components.MainScreenComponent
import javax.inject.Inject

class MainFirstFragment : BaseFragment() {

    @JvmField
    @Inject
    var mRouter: SecondFragmentRouter? = null

    private lateinit var mSecondFragmentButton: Button

    override fun getFragmentLayoutId(): Int {
        return R.layout.fragment_main_first
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getComponent(MainScreenComponent::class.java)
            .plusMainFirstViewComponent()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mSecondFragmentButton = view.findViewById(R.id.button_second_fragment)

        mSecondFragmentButton.setOnClickListener {
            mRouter?.onSwitchToSecondFragment()
        }
    }

    interface SecondFragmentRouter {
        fun onSwitchToSecondFragment()
    }
}