package com.marcosvbras.robomarket.home.viewmodel;

import android.annotation.SuppressLint;

import com.marcosvbras.robomarket.R;
import com.marcosvbras.robomarket.app.App;
import com.marcosvbras.robomarket.business.model.UserModel;
import com.marcosvbras.robomarket.interfaces.BaseActivityCallback;
import com.marcosvbras.robomarket.login.ui.LoginActivity;
import com.marcosvbras.robomarket.profile.ui.EditProfileActivity;
import com.marcosvbras.robomarket.viewmodels.BaseViewModel;

import io.reactivex.disposables.Disposable;

public class ProfileViewModel extends BaseViewModel {

    private BaseActivityCallback activityCallback;
    private UserModel userModel;
    private Disposable disposable;

    public ProfileViewModel(BaseActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
        userModel = new UserModel();
    }

    public void edit() {
        activityCallback.openActivity(EditProfileActivity.class, false);
    }

    @SuppressLint("CheckResult")
    public void requestPasswordReset() {
        cleanupSubscriptions();

        userModel.resetPassword(App.getInstance().getUser())
                .subscribe(next -> {

                }, error -> {
                    isLoading.set(false);
                    activityCallback.showDialogMessage(R.string.sucessful_reset_password);
                }, () -> isLoading.set(false), d -> {
                    isLoading.set(true);
                    disposable = d;
                });
    }

    @SuppressLint("CheckResult")
    public void deleteAccount() {
        cleanupSubscriptions();

        userModel.deleteUser(App.getInstance().getUser().getObjectId())
                .subscribe(next -> {

                }, error -> {
                    isLoading.set(false);
                    activityCallback.showDialogMessage(error.getMessage());
                }, () -> {
                    isLoading.set(false);
                    activityCallback.openActivity(LoginActivity.class, true);
                }, d -> {
                    isLoading.set(true);
                    disposable = d;
                });
    }

    @Override
    protected void onCleared() {
        cleanupSubscriptions();
        super.onCleared();
    }

    @Override
    public void cleanupSubscriptions() {
        if(disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
