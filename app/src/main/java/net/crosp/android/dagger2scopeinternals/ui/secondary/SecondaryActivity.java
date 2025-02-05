package net.crosp.android.dagger2scopeinternals.ui.secondary;

import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import net.crosp.android.dagger2scopeinternals.R;
import net.crosp.android.dagger2scopeinternals.core.activity.BaseSingleFragmentActivity;
import net.crosp.android.dagger2scopeinternals.di.contract.ProvidesComponent;
import net.crosp.android.dagger2scopeinternals.ui.main.MainActivity;
import net.crosp.android.dagger2scopeinternals.module.secondary.di.components.SecondaryScreenComponent;
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.contract.CarDataRepositoryContract;
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.contract.GlobalEventNotifierContract;
import net.crosp.android.dagger2scopeinternals.data.shareddependencies.implementation.SomeEvent;

import javax.inject.Inject;

public class SecondaryActivity extends BaseSingleFragmentActivity implements ProvidesComponent<SecondaryScreenComponent>, SecondaryFirstFragment.SecondFragmentRouter, SecondarySecondFragment.FirstFragmentRouter, GlobalEventNotifierContract.EventListener<SomeEvent> {
    @Inject
    GlobalEventNotifierContract<SomeEvent> mGlobalEventNotifier;
    // Views
    @Inject
    CarDataRepositoryContract mCarRepoOther;
//    @BindView(R.id.toolbar_main)
    Toolbar mMainToolbar;
    // UI Variables
    ActionBar mActionBar;
    private SecondaryScreenComponent mSecondaryScreenComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inject views
        injectDependencies();
        initUI();
        navigateToInitialScreen();
    }

    private void initUI() {
        initToolbar();
    }

    private void injectDependencies() {
        mSecondaryScreenComponent = mActivityComponent.plusSecondaryScreenComponent();
        mSecondaryScreenComponent.inject(this);
    }

    public void navigateToInitialScreen() {
        replaceFragment(SecondaryFirstFragment.class, false);
    }

    protected void initToolbar() {
        mMainToolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(mMainToolbar);
        mActionBar = getSupportActionBar();
    }
    // Layout configuration

    @Override
    public int getMainLayoutId() {
        return R.layout.activity_secondary;
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.content_toolbar_single_fragment;
    }

    @Override
    public int getFragmentContainerLayoutId() {
        return R.id.frame_layout_fragment_container;
    }

    @Override
    public int getContentContainerId() {
        return R.id.linear_layout_content;
    }

    @Override
    public void onSwitchToSecondFragment() {
        replaceFragment(SecondarySecondFragment.class, false);

    }

    @Override
    public void onSwitchBackToMainActivity() {
        startNewActivity(MainActivity.class);
    }

    @Override
    public void onSwitchToFirstFragment() {
        replaceFragment(SecondaryFirstFragment.class, false);

    }

    @Override
    public SecondaryScreenComponent getComponent() {
        return mSecondaryScreenComponent;
    }

    @Override
    public void onEvent(SomeEvent event) {

    }

    // Routing

}
