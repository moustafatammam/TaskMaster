package com.projects.android.ui.userInterface.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.projects.android.ui.R;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidInjection.inject(this);

    }

}
