package com.twelve.latesleeper.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.twelve.latesleeper.R;
import com.twelve.latesleeper.database.Database;
import com.twelve.latesleeper.model.Goal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ViewGoalsActivity  extends AppCompatActivity
{
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    ProgressBar loadingBar;
    ConstraintLayout dimLayout;
    ListView goalListView;
    Context context;

    private List<Date> dates = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private List<String> bodies = new ArrayList<>();
    private List<Integer> days = new ArrayList<>();
    private List<Integer> daysCompleted = new ArrayList<>();
    private List<Goal> goals = new ArrayList<>();
    private List<String> goalIDs = new ArrayList<>();

    int images[] = {R.drawable.darkred, R.drawable.orange, R.drawable.yellow, R.drawable.pink, R.drawable.purple, R.drawable.blue, R.drawable.green};

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
        dimLayout.setVisibility(View.VISIBLE);
        dimLayout.bringToFront();
        retrieveGoals();
    }

    public void retrieveGoals() {
        Database.getDatabase().collection("users").document(currentUser.getUid()).collection("goals")
                .orderBy("dateCreated", Query.Direction.ASCENDING)
        .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot DocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                titles.clear();
                Calendar cal = Calendar.getInstance();

                for (DocumentSnapshot snapshot : DocumentSnapshots) {

                    Timestamp timestamp = snapshot.getTimestamp("dateCreated");
                    Date date = timestamp.toDate();

                    goals.add(new Goal(snapshot.getString("sleepTime"), parseInt(snapshot.get("days").toString()),
                            parseInt(snapshot.get("daysCompleted").toString()), parseInt(snapshot.get("totalHours").toString()),
                            date, snapshot.getBoolean("completed")));

                    goalIDs.add(snapshot.getId());

                    dates.add(date);

                    String[] bedTime = snapshot.get("sleepTime").toString().split(":");
                    cal.set(Calendar.HOUR_OF_DAY, parseInt(bedTime[0]));
                    cal.set(Calendar.MINUTE, parseInt(bedTime[1]));
                    Date d = cal.getTime();
                    DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
                    titles.add("Bedtime: " + dateFormat.format(d).toUpperCase());

                    days.add(parseInt(snapshot.get("days").toString()));
                    daysCompleted.add(parseInt(snapshot.get("daysCompleted").toString()));

                    bodies.add(snapshot.get("daysCompleted").toString() + "/" + snapshot.get("days").toString() + " Days Complete");
                }
                String[] arrayTitle = titles.toArray(new String[0]);
                String[] arrayBody = bodies.toArray(new String[0]);
                goalAdapter adapter = new goalAdapter(getApplicationContext(), arrayTitle, arrayBody, images);
                adapter.notifyDataSetChanged();
                goalListView.setAdapter(adapter);

                goalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(ViewGoalsActivity.this, ViewSpecificGoalActivity.class);
                        intent.putExtra("goal", goals.get(i));
                        intent.putExtra("goalID", goalIDs.get(i));
                        startActivity(intent);
                    }
                });

                dimLayout.setVisibility(View.GONE);
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
            ImageView image = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.titleTextView);
            TextView myBody = row.findViewById(R.id.bodyTextView);

            myTitle.setTextColor(Color.parseColor("#AEAEAE"));

            float ratio = (float)daysCompleted.get(position) / days.get(position);
            ratio *= 100;
            if (ratio < 14.28) image.setImageResource(rImgs[0]);
            else if (ratio < 28.56) image.setImageResource(rImgs[1]);
            else if (ratio < 42.84) image.setImageResource(rImgs[2]);
            else if (ratio < 57.12) image.setImageResource(rImgs[3]);
            else if (ratio < 71.4) image.setImageResource(rImgs[4]);
            else if (ratio < 85.68) image.setImageResource(rImgs[5]);
            else image.setImageResource(rImgs[6]);

            if (ratio == 100) {
                myBody.setTextColor(Color.parseColor("#22B14C"));
            }

            myTitle.setText(rTitle[position]);
            myBody.setText(rBody[position]);

            return row;
        }
    }
}
