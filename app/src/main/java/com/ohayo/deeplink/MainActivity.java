package com.ohayo.deeplink;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;
import io.branch.referral.Branch;
import io.branch.referral.BranchError;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Branch.sessionBuilder(this).withCallback(branchReferralInitListener).withData(getIntent() != null ? getIntent().getData() : null).init();
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.setIntent(intent);
    }

    private Branch.BranchReferralInitListener branchReferralInitListener = new Branch.BranchReferralInitListener(){
        @Override
        public void onInitFinished(@Nullable JSONObject referringParams, @Nullable BranchError error) {
            if (error == null) {

                // option 1: log data
                Log.i("BRANCH SDK", referringParams.toString());
                /*
                // option 2: save data to be used later
                SharedPreferences preferences = MainActivity.this.getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
                preferences.edit().putString("branchData", referringParams.toString()).apply();
                */
                // option 3: navigate to page
                Intent intent = new Intent(MainActivity.this, DeepLinkActivity.class);
                startActivity(intent);

                // option 4: display data
                Toast.makeText(MainActivity.this, referringParams.toString(), Toast.LENGTH_LONG).show();

            } else {
                Log.i("BRANCH SDK", error.getMessage());
            }
        }
    };
}