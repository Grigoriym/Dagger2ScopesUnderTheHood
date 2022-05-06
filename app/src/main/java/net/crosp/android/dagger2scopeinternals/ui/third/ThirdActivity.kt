package net.crosp.android.dagger2scopeinternals.ui.third

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import net.crosp.android.dagger2scopeinternals.Dagger2ScopeInternalsApplication
import net.crosp.android.dagger2scopeinternals.R
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.ActivityInjectScopedRepo
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.ActivityScopedRepo
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.Repo1
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.Repo2
import net.crosp.android.dagger2scopeinternals.di.component.activity.DaggerThirdActivityComponent
import net.crosp.android.dagger2scopeinternals.di.component.activity.ThirdActivityComponent
import javax.inject.Inject

class ThirdActivity : AppCompatActivity() {

    @Inject
    lateinit var activityScopedRepo: ActivityScopedRepo

    @Inject
    lateinit var activityInjectScopedRepo: ActivityInjectScopedRepo

    @Inject
    lateinit var repo1: Repo1

    @Inject
    lateinit var repo2: Repo2

    private val component: ThirdActivityComponent by lazy {
        DaggerThirdActivityComponent
            .factory()
            .create(
                this,
                (application as Dagger2ScopeInternalsApplication).component
            )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        println("asd ThirdActivity scoped $activityScopedRepo")
        println("asd ThirdActivity injectScoped $activityInjectScopedRepo")
        println("asd ThirdActivity repo1 $repo1")
        println("asd ThirdActivity repo2 $repo2")

        findViewById<Button>(R.id.btnFirst).setOnClickListener {
            startActivity(Intent(this, FourthActivity::class.java))
        }
    }

}