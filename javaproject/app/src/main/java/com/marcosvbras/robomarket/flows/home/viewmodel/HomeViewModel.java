package com.marcosvbras.robomarket.flows.home.viewmodel;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.flows.home.interfaces.HomeActivityCallbacks;
import com.marcosvbras.robomarket.flows.home.ui.fragment.ProfileFragment;
import com.marcosvbras.robomarket.flows.home.ui.fragment.RobotsFragment;
import com.marcosvbras.robomarket.flows.home.ui.fragment.SalesFragment;
import com.marcosvbras.robomarket.utils.Constants;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;

public class HomeViewModel extends BaseViewModel {

    private HomeActivityCallbacks activityCallback;
    private SalesFragment salesFragment;
    private RobotsFragment robotsFragment;
    private ProfileFragment profileFragment;

    public HomeViewModel(HomeActivityCallbacks activityCallback) {
        this.activityCallback = activityCallback;
        salesFragment = new SalesFragment();
        robotsFragment = new RobotsFragment();
        profileFragment = new ProfileFragment();
        this.activityCallback.replaceFragment(salesFragment, Constants.Other.SALES_FRAGMENT_TAG);
    }

    public boolean onNavigationClick(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.item_sales:
                activityCallback.replaceFragment(salesFragment, Constants.Other.SALES_FRAGMENT_TAG);
                break;
            case R.id.item_robots:
                activityCallback.replaceFragment(robotsFragment, Constants.Other.ROBOTS_FRAGMENT_TAG);
                break;
            case R.id.item_profile:
                activityCallback.replaceFragment(profileFragment, Constants.Other.PROFILE_FRAGMENT_TAG);
                break;
        }

        return true;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    @Override
    public void cleanupSubscriptions() {

    }
}