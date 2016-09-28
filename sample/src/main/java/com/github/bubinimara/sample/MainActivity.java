package com.github.bubinimara.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.github.bubinimara.preferencemanager.PreferenceManager;

import static com.github.bubinimara.sample.R.id.fab;

public class MainActivity extends AppCompatActivity {


    private PreferenceManager<SessionInfo> preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferenceManager = PreferenceManager.getPreferenceManager(this, SessionInfo.class);
        SessionInfo sessionInfo = preferenceManager.load();


        TextView textView = (TextView) findViewById(R.id.textView);
        if(sessionInfo!=null)
            textView.setText(sessionInfo.getMail());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void logout(){
        preferenceManager.clear();

        Intent intent = new Intent(this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
