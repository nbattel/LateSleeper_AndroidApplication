package com.twelve.latesleeper.test.automated;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import com.twelve.latesleeper.activity.CreateAccountActivity;

import com.twelve.latesleeper.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class CreateAccountTest {

    @Rule
    public ActivityTestRule<CreateAccountActivity> CreateAccountActivityTestRule = new ActivityTestRule<CreateAccountActivity>(CreateAccountActivity.class);

    private String emailAddress = "test@test.com",
            password = "test",
            confirmPassword = "test",
            firstName = "test",
            lastName = "test";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateAccount() {
        // Input some text in the edit text
        Espresso.onView(withId(R.id.txtEmail)).perform(typeText(emailAddress));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.txtPass)).perform(typeText(password));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.txtPassConfirm)).perform(typeText(confirmPassword));
        Espresso.closeSoftKeyboard();

        // Swipe up to reach the bottom of the screen
        Espresso.onView(withId(R.id.scrollView2))
                .perform(swipeUp());

        Espresso.onView(withId(R.id.txtFirstName)).perform(typeText(firstName));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.txtLastName)).perform(typeText(lastName));
        Espresso.closeSoftKeyboard();

        // Perform button click
        Espresso.onView(withId(R.id.btnConfirm)).perform(click());
    }
}