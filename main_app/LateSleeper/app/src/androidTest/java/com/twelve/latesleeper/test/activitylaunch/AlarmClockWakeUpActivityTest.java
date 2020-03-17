package com.twelve.latesleeper.test.activitylaunch;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.twelve.latesleeper.R;
import com.twelve.latesleeper.activity.AlarmClockWakeUpActivity;
import static androidx.test.espresso.Espresso.onView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AlarmClockWakeUpActivityTest {

    @Rule
    public ActivityTestRule<AlarmClockWakeUpActivity> AlarmClockWakeUpActivityTestRule = new ActivityTestRule<AlarmClockWakeUpActivity>(AlarmClockWakeUpActivity.class);

    private AlarmClockWakeUpActivity AlarmClockWakeUpActivity = null;

    @Before
    public void setUp() throws Exception {
        AlarmClockWakeUpActivity = AlarmClockWakeUpActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = AlarmClockWakeUpActivity.findViewById(R.id.setAlarmBtn);

        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        AlarmClockWakeUpActivity = null;
    }
}