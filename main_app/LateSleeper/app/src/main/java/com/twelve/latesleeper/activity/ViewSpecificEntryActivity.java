package com.twelve.latesleeper.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.twelve.latesleeper.R;
import com.twelve.latesleeper.model.Entry;

import java.util.HashMap;

public class ViewSpecificEntryActivity extends AppCompatActivity {

    private Entry entry;
    private HashMap<String, String> entryInfo;

    ProgressBar loadingBar;
    ConstraintLayout dimLayout;

    TextView entryTitle;
    TextView entryBody;
    TextView entryDate;

    private String title;
    private String body;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_specific_entry);

        loadingBar = (ProgressBar) findViewById(R.id.loadingBar);
        dimLayout = (ConstraintLayout) findViewById(R.id.dimLayout);

        entryTitle = findViewById(R.id.entryTitle);
        entryBody = findViewById(R.id.entryBody);
        entryDate = findViewById(R.id.entryDate);

        entry = (Entry) getIntent().getSerializableExtra("entry");
        entryInfo = entry.getEntry();
    }

    @Override
    public void onStart() {
        super.onStart();

        dimLayout.setVisibility(View.VISIBLE);
        dimLayout.bringToFront();

        title = entryInfo.get("title");
        body = entryInfo.get("body");
        date = entryInfo.get("date");

        entryTitle.setText(title);
        entryBody.setText(body);
        entryDate.setText(date);

        dimLayout.setVisibility(View.GONE);

    }
}
