package net.crosp.android.dagger2scopeinternals.ui.secondary

import android.os.Bundle
import android.view.View
import android.widget.Button
import net.crosp.android.dagger2scopeinternals.R
import net.crosp.android.dagger2scopeinternals.core.fragment.BaseFragment
import net.crosp.android.dagger2scopeinternals.module.secondary.di.components.SecondaryScreenComponent
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.contract.CarPartsDataRepositoryContract
import javax.inject.Inject

class SecondarySecondFragment : BaseFragment() {

    @JvmField
    @Inject
    var mCarPartsDataRepository: CarPartsDataRepositoryContract? = null

    @JvmField
    @Inject
    var mRouter: FirstFragmentRouter? = null

    private lateinit var fragmentButton: Button

    override fun getFragmentLayoutId(): Int {
        return R.layout.fragment_secondary_second
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getComponent(SecondaryScreenComponent::class.java)
            .plusSecondarySecondViewComponent()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentButton = view.findViewById(R.id.button_first_fragment)

        fragmentButton.setOnClickListener {
            mRouter?.onSwitchToFirstFragment()
        }
    }

    interface FirstFragmentRouter {
        fun onSwitchToFirstFragment()
    }
}