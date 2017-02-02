package com.elorri.android.loaders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e("App", Thread.currentThread().getStackTrace()[2] + "");
        super.onDestroy();
    }
}
