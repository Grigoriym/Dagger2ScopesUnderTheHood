package net.crosp.android.dagger2scopeinternals.ui.third

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.crosp.android.dagger2scopeinternals.Dagger2ScopeInternalsApplication
import net.crosp.android.dagger2scopeinternals.R
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.ActivityInjectScopedRepo
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.ActivityScopedRepo
import net.crosp.android.dagger2scopeinternals.di.component.activity.DaggerFourthActivityComponent
import net.crosp.android.dagger2scopeinternals.di.component.activity.DaggerThirdActivityComponent
import net.crosp.android.dagger2scopeinternals.di.component.activity.FourthActivityComponent
import net.crosp.android.dagger2scopeinternals.di.component.activity.ThirdActivityComponent
import javax.inject.Inject

class FourthActivity : AppCompatActivity() {

    @Inject
    lateinit var activityScopedRepo: ActivityScopedRepo

    @Inject
    lateinit var activityInjectScopedRepo: ActivityInjectScopedRepo

    private val component: FourthActivityComponent by lazy {
        DaggerFourthActivityComponent
            .factory()
            .create(
                this,
                (application as Dagger2ScopeInternalsApplication).component
            )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)

        println("asd ThirdActivity scoped $activityScopedRepo")
        println("asd ThirdActivity injectScoped $activityInjectScopedRepo")
    }
}