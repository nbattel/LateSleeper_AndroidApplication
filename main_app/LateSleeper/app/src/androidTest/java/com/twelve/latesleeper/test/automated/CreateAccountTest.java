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
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class CreateAccountTest {

    @Rule
    public ActivityTestRule<MainActivity> CreateAccountActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private String emailAddress, password, confirmPassword, firstName, lastName;

    @Before
    public void setUp() throws Exception {
        emailAddress = "test@test.com";
        password = "test";
        confirmPassword = "test";
        firstName = "test";
        lastName = "test";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateAccount() {
        goToCreateAccount();

        fillForm();

        onView(withId(R.id.btnConfirm)).perform(click());
    }

    public void goToCreateAccount(){
        sleep(1000);
        onView(withId(R.id.imageView)).perform(doubleClick());

        sleep(2000);
        onView(withId(R.id.createAccountButton)).perform(click());

        sleep(2000);
    }

    public void fillForm(){
        onView(withId(R.id.txtEmail)).perform(typeText(emailAddress));
        closeSoftKeyboard();

        onView(withId(R.id.txtPass)).perform(typeText(password));
        closeSoftKeyboard();

        onView(withId(R.id.txtPassConfirm)).perform(typeText(confirmPassword));
        closeSoftKeyboard();

        onView(withId(R.id.scrollView2)).perform(swipeUp());

        onView(withId(R.id.txtFirstName)).perform(typeText(firstName));
        closeSoftKeyboard();

        onView(withId(R.id.txtLastName)).perform(typeText(lastName));
        closeSoftKeyboard();

        sleep(2000);
    }

    public void sleep(int time){
        SystemClock.sleep(time);
    }
}