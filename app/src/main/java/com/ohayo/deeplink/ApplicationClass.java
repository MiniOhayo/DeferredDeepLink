package com.ohayo.deeplink;

import android.app.Application;

import io.branch.referral.Branch;

public class ApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Branch logging for debugging
        Branch.enableLogging();

        // Initialize the Branch object
        Branch.getAutoInstance(this);
    }
}
