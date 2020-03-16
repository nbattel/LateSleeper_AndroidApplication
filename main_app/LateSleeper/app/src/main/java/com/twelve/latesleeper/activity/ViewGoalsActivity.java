package com.twelve.latesleeper.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.twelve.latesleeper.R;
import com.twelve.latesleeper.database.Database;

import java.util.ArrayList;
import java.util.List;

public class ViewGoalsActivity  extends AppCompatActivity
{
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    ProgressBar loadingBar;
    ConstraintLayout dimLayout;
    ListView goalListView;
    Context context;

    private List<String> titles = new ArrayList<>();
    private List<String> bodies = new ArrayList<>();

    int images[] = {R.drawable.blue, R.drawable.darkred, R.drawable.yellow, R.drawable.green, R.drawable.purple, R.drawable.pink, R.drawable.orange};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_viewgoals);

        context = this;

        loadingBar = (ProgressBar) findViewById(R.id.loadingBar);
        dimLayout = (ConstraintLayout) findViewById(R.id.dimLayout);

        goalListView = (ListView) findViewById(R.id.goalListView);

    }

    @Override
    public void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
        dimLayout.bringToFront();
        loadingBar.bringToFront();
        loadingBar.setVisibility(View.VISIBLE);
        dimLayout.setVisibility(View.VISIBLE);
        retrieveGoals();
    }

    public void retrieveGoals() {
        Database.getDatabase().collection("users").document(currentUser.getUid()).collection("goals")
        .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot DocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                titles.clear();

                for (DocumentSnapshot snapshot : DocumentSnapshots) {
                    Timestamp timestamp = snapshot.getTimestamp("dateCreated");
                    titles.add(timestamp.toDate().toString());
                    bodies.add(snapshot.get("daysCompleted").toString() + "/" + snapshot.get("days").toString() + " days completed.");
                }
                String[] arrayTitle = titles.toArray(new String[0]);
                String[] arrayBody = bodies.toArray(new String[0]);
                goalAdapter adapter = new goalAdapter(getApplicationContext(), arrayTitle, arrayBody, images);
                adapter.notifyDataSetChanged();
                goalListView.setAdapter(adapter);
            }
        });
    }

    class goalAdapter extends ArrayAdapter<String> {
        Context context;
        String rTitle[];
        String rBody[];
        int rImgs[];

        goalAdapter(Context c, String title[], String body[], int imgs[]) {
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
            ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.titleTextView);
            TextView myBody = row.findViewById(R.id.bodyTextView);

            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            myBody.setText(rBody[position]);

            return row;
        }
    }
}
