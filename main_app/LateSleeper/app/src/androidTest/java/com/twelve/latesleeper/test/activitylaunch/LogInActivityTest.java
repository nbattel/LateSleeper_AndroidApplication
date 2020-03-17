package com.twelve.latesleeper.test.activitylaunch;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.twelve.latesleeper.R;
import com.twelve.latesleeper.activity.LogInActivity;
import static androidx.test.espresso.Espresso.onView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogInActivityTest {

    @Rule
    public ActivityTestRule<LogInActivity> logInActivityTestRule = new ActivityTestRule<LogInActivity>(LogInActivity.class);

    private LogInActivity logInActivity = null;

    @Before
    public void setUp() throws Exception {
        logInActivity = logInActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = logInActivity.findViewById(R.id.logInTextView);

        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        logInActivity = null;
    }
}