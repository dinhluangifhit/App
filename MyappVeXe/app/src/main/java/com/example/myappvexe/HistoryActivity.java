package com.example.myappvexe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        final Fragment fragment1 = new HistoryFragment();
        final Fragment fragment2 = new BeginFragment();

        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        final TextView textView1 = findViewById(R.id.textView12);
        final TextView textView2 = findViewById(R.id.textView13);

        textView1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.fragment_container, fragment1);
                fragmentTransaction.commit();

            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.fragment_container, fragment2);
                fragmentTransaction.commit();
            }
        });

    }
}