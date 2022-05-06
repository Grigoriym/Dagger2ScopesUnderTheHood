package net.crosp.android.dagger2scopeinternals.data.shareddependencies

import android.content.Context
import net.crosp.android.dagger2scopeinternals.di.qualifiers.QualifierActivityContext
import net.crosp.android.dagger2scopeinternals.di.scope.PerActivity
import javax.inject.Inject

@PerActivity
class Repo1Impl @Inject constructor(
    @QualifierActivityContext private val context: Context
) : Repo1 {
}