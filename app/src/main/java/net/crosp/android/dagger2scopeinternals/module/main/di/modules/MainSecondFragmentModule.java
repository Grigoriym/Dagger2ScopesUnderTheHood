package net.crosp.android.dagger2scopeinternals.module.main.di.modules;

import android.app.Activity;

import net.crosp.android.dagger2scopeinternals.di.scope.PerFragment;
import net.crosp.android.dagger2scopeinternals.ui.main.MainActivity;
import net.crosp.android.dagger2scopeinternals.ui.main.MainSecondFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexander Molochko on 10/27/18.
 * Project Dagger2ScopeInternals
 * Copyright (C) 2018 CROSP Solutions
 */
@Module
public class MainSecondFragmentModule {
    @Provides
    @PerFragment
    public MainSecondFragment.FirstFragmentRouter provideFirstFragmentRouter(Activity containerActivity) {
        return (MainActivity) containerActivity;
    }
}
