package com.twelve.latesleeper.test.automated;

import android.os.SystemClock;

import androidx.test.rule.ActivityTestRule;

import com.twelve.latesleeper.R;
import com.twelve.latesleeper.activity.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class LogInTest {

    @Rule
    public ActivityTestRule<MainActivity> LogInTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private String emailAddress, password;

    @Before
    public void setUp() throws Exception {
        emailAddress = "tfouxman@gmail.com";
        password = "test123";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testLogIn(){
        sleep(5000);

        fillForm();

        onView(withId(R.id.logInButton)).perform(click());

        sleep(5000);

        onView(withId(R.id.signOut)).perform(click());
    }

    public void fillForm(){
        onView(withId(R.id.txtEmail)).perform(typeText(emailAddress));
        closeSoftKeyboard();

        onView(withId(R.id.txtPass)).perform(typeText(password));
        closeSoftKeyboard();
    }

    public void sleep(int time){
        SystemClock.sleep(time);
    }
}