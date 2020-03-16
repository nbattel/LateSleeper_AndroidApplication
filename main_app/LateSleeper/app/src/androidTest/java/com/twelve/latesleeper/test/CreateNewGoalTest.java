package com.twelve.latesleeper.test;

import android.os.SystemClock;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.GeneralLocation;
import androidx.test.espresso.action.GeneralSwipeAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Swipe;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;



import com.twelve.latesleeper.R;
import com.twelve.latesleeper.activity.LogInActivity;
import com.twelve.latesleeper.test.automated.LogInTest;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class CreateNewGoalTest {

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
    public void testCreateNewGoal(){
        // Log in activity
        onView(withId(com.twelve.latesleeper.R.id.txtEmail)).perform(typeText(emailAddress));
        Espresso.closeSoftKeyboard();

        // Input some text in the edit text
        onView(withId(com.twelve.latesleeper.R.id.txtPass)).perform(typeText(password));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.logInButton)).perform(click());

        SystemClock.sleep(4000);

        // User home activity
        onView(withId(R.id.journalBtn)).perform(click());

        SystemClock.sleep(3000);

        onView(withId(R.id.createGoalScrollView))
                .perform(swipeDown());
        onView(withId(R.id.createGoalScrollView))
                .perform(swipeDown());

        onView(withId(R.id.continueButton)).perform(click());

        // Create Goal Settings Activity
        SystemClock.sleep(2000);

        setTime(19,30);

        SystemClock.sleep(2000);

        onView(withId(R.id.goalSettingsScrollView))
                .perform(swipeDownFast());

        ViewInteraction numPicker = onView(withId(R.id.numberPicker));
        numPicker.perform(setNumber(2));
        SystemClock.sleep(500);
        numPicker.perform(setNumber(3));
        SystemClock.sleep(500);
        numPicker.perform(setNumber(4));
        SystemClock.sleep(500);
        numPicker.perform(setNumber(2));

        SystemClock.sleep(2000);

        onView(withId(R.id.goalSettingsScrollView))
                .perform(swipeDownFast());

        onView(withId(R.id.button2)).perform(click());

         SystemClock.sleep(2000);

        onView(withId(R.id.goalListView))
                .perform(swipeDownFast());
        onView(withId(R.id.goalListView))
                .perform(swipeDownFast());

        SystemClock.sleep(4000);

        onView(isRoot()).perform(ViewActions.pressBack());
        SystemClock.sleep(4000);

        Espresso.onView(withId(R.id.signOut)).perform(click());
        SystemClock.sleep(4000);


    }

    public void setTime(int hour, int minutes) {
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hour, minutes));
    }

    public static ViewAction swipeDown() {
        return new GeneralSwipeAction(Swipe.SLOW, GeneralLocation.BOTTOM_CENTER,
                GeneralLocation.TOP_CENTER, Press.FINGER
        );
    }
    public static ViewAction swipeDownFast() {
        return new GeneralSwipeAction(Swipe.FAST, GeneralLocation.BOTTOM_CENTER,
                GeneralLocation.TOP_CENTER, Press.FINGER
        );
    }

    public static ViewAction setNumber(final int num) {
        return new ViewAction() {
            @Override
            public void perform(UiController uiController, View view) {
                NumberPicker np = (NumberPicker) view;
                np.setValue(num);

            }

            @Override
            public String getDescription() {
                return "Set the passed number into the NumberPicker";
            }

            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(NumberPicker.class);
            }
        };
    }
}