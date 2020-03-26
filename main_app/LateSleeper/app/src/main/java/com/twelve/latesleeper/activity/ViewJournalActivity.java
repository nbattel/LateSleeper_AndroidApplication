package com.twelve.latesleeper.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.twelve.latesleeper.R;
import com.twelve.latesleeper.database.Database;
import com.twelve.latesleeper.model.Entry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ViewJournalActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    ProgressBar loadingBar;
    ConstraintLayout dimLayout;
    ListView goalListView;
    Context context;
    TextView titleText;
    boolean journalType;
    String path;

    private List<String> dates = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private List<String> bodies = new ArrayList<>();
    private List<Entry> entries = new ArrayList<>();
    private List<String> entryIDs = new ArrayList<>();

    int images[] = {R.drawable.darkred, R.drawable.orange, R.drawable.yellow, R.drawable.pink, R.drawable.purple, R.drawable.blue, R.drawable.green};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_view_journal);

        journalType = getIntent().getBooleanExtra("journalType", false);

        context = this;

        loadingBar = (ProgressBar) findViewById(R.id.loadingBar);
        dimLayout = (ConstraintLayout) findViewById(R.id.dimLayout);
        titleText = (TextView) findViewById(R.id.titleText);

        goalListView = (ListView) findViewById(R.id.goalListView);
    }

    @Override
    public void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();

        dimLayout.setVisibility(View.VISIBLE);
        dimLayout.bringToFront();

        if (journalType) {
            path = "relabelJournal";
            titleText.setText("Relabel Journal");
        }
        else {
            path = "revalueJournal";
            titleText.setText("Revalue Journal");
        }

        retrieveJournal();
    }

    public void retrieveJournal() {
        Database.getDatabase().collection("users").document(currentUser.getUid()).collection("goals")
                .document(ViewSpecificGoalActivity.goalID)
                .collection(path)
                .orderBy("date", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot DocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                        titles.clear();
                        Calendar cal = Calendar.getInstance();

                        for (DocumentSnapshot snapshot : DocumentSnapshots) {

                            String timestamp = snapshot.getString("date");

                            entries.add(new Entry(snapshot.getString("body"), snapshot.getString("title"), timestamp));

                            entryIDs.add(snapshot.getId());

                            dates.add(timestamp);

                            titles.add(snapshot.getString("title"));

                            bodies.add(snapshot.getString("body"));
                        }
                        String[] arrayTitle = titles.toArray(new String[0]);
                        String[] arrayBody = dates.toArray(new String[0]);
                        journalAdapter adapter = new journalAdapter(getApplicationContext(), arrayTitle, arrayBody, images);
                        adapter.notifyDataSetChanged();
                        goalListView.setAdapter(adapter);

                        goalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(ViewJournalActivity.this, ViewSpecificEntryActivity.class);
                                intent.putExtra("entry", entries.get(i));
                                startActivity(intent);
                            }
                        });

                        dimLayout.setVisibility(View.GONE);
                    }
                });
    }

    class journalAdapter extends ArrayAdapter<String> {
        Context context;
        String rTitle[];
        String rBody[];
        int rImgs[];

        journalAdapter(Context c, String title[], String body[], int imgs[]) {
            super(c, R.layout.row, R.id.titleTextView, title);
            this.context = c;
            this.rTitle = title;
            this.rBody = body;
            this.rImgs = imgs;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView image = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.titleTextView);
            TextView myBody = row.findViewById(R.id.bodyTextView);

            myTitle.setTextColor(Color.parseColor("#AEAEAE"));

            String title = rTitle[position];
            Log.d("TAG", "getView: *" + title + "*");

            if (title.contains("nervous")) {
                image.setImageResource(rImgs[0]);
            }

            else if (title.contains("anxious")) {
                image.setImageResource(rImgs[1]);
            }

            else if (title.contains("missing")) {
                image.setImageResource(rImgs[4]);
            }

            else image.setImageResource(rImgs[5]);

            myTitle.setText(title);
            myBody.setText(rBody[position]);

            return row;
        }
    }
}
