package com.twelve.latesleeper.test.automated;

import android.os.SystemClock;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import com.twelve.latesleeper.R;
import com.twelve.latesleeper.activity.CreateAccountActivity;
import com.twelve.latesleeper.activity.LogInActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class LogInTest {

    @Rule
    public ActivityTestRule<LogInActivity> LogInTestRule = new ActivityTestRule<LogInActivity>(LogInActivity.class);

    private String emailAddress = "tfouxman@gmail.com", password = "test123";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void logIn(){
        // Input some text in the edit text
        Espresso.onView(withId(R.id.txtEmail)).perform(typeText(emailAddress));
        Espresso.closeSoftKeyboard();

        // Input some text in the edit text
        Espresso.onView(withId(R.id.txtPass)).perform(typeText(password));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.logInButton)).perform(click());

        SystemClock.sleep(4000);

        Espresso.onView(withId(R.id.button)).perform(click());

    }
}