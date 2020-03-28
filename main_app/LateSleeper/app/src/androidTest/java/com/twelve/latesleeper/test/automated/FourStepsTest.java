package com.twelve.latesleeper.test.automated;

import android.os.SystemClock;
import android.widget.TimePicker;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.GeneralLocation;
import androidx.test.espresso.action.GeneralSwipeAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Swipe;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.rule.ActivityTestRule;

import com.twelve.latesleeper.R;
import com.twelve.latesleeper.activity.MainActivity;

import junit.framework.AssertionFailedError;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;

public class FourStepsTest {
    @Rule
    public ActivityTestRule<MainActivity> FourStepsTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

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
    public void testStartGoal(){

        try {
            onView(withId(R.id.signOut)).perform(click());
        } catch (NoMatchingViewException e) {
            // no user is logged in
        }

        goToLogin();

        fillForm();
        sleep(5000);
        onView(withId(R.id.logInButton)).perform(click());

        // UserHomeActivity
        sleep(2000);

        goToGoal();

        onView(withId(R.id.startGoal)).perform(click());

        // Relabel
        relabelInput();

        onView(withId(R.id.nextButton)).perform(click());

        sleep(2000);

        // Reframe
        reframeInput();

        onView(withId(R.id.nextButton)).perform(click());

        sleep(3000);

        onView(withId(R.id.nextButton)).perform(click());

        sleep(3000);

        onView(withId(R.id.nextButton)).perform(click());

        sleep(3000);

        setTime(22, 45);

        sleep(2000);

        onView(withId(R.id.setAlarmBtn)).perform(click());

        sleep(2000);

        backToUserHome();

        onView(withId(R.id.signOut)).perform(click());

        sleep(2000);

        /*
        * END OF TEST
        * */
    }

    public void goToLogin(){
        sleep(1000);
        onView(withId(R.id.imageView)).perform(doubleClick());
    }

    public void fillForm(){
        onView(withId(R.id.txtEmail)).perform(typeText(emailAddress));
        closeSoftKeyboard();

        onView(withId(R.id.txtPass)).perform(typeText(password));
        closeSoftKeyboard();
    }

    public void goToGoal(){

        onView(withId(R.id.newEntry)).perform(click());

        // ViewGoalsActivity
        sleep(4000);

        onData(anything()).inAdapterView(withId(R.id.goalListView)).atPosition(2).perform(click());

        // ViewGoalActivity
        sleep(4000);

        onView(withId(R.id.viewGoalScrollView)).perform(swipeDownSlow());

        sleep(1000);
    }

    public void relabelInput(){
        onView(withId(R.id.feelingDropdown)).perform(click());
        sleep(500);
        onData(allOf(is(instanceOf(String.class)), is("I feel anxious"))).perform(click());
        sleep(500);

        sleep(1500);

        onView(withId(R.id.relabelScrollView)).perform(swipeDownSlow());

        sleep(1500);

        onView(withId(R.id.bodyEditText)).perform(typeText("Dear journal, \n Today I am feeling anxious and I cannot sleep. Im not feeling good. \n Team 12"));
        closeSoftKeyboard();

        sleep(2000);
    }

    public void reframeInput(){
        // Question 1
        onView(withId(R.id.question1Dropdown)).perform(click());
        sleep(500);
        onData(allOf(is(instanceOf(String.class)), is("2 (Sometimes)"))).perform(click());
        sleep(500);

        // Question 2
        onView(withId(R.id.question2Dropdown)).perform(click());
        sleep(500);
        onData(allOf(is(instanceOf(String.class)), is("4 (Always)"))).perform(click());
        sleep(500);

        onView(withId(R.id.reframeScrollView)).perform(swipeUp());

        sleep(2000);

        // Question 3
        onView(withId(R.id.question3Dropdown)).perform(click());
        sleep(500);
        onData(allOf(is(instanceOf(String.class)), is("3 (Often)"))).perform(click());
        sleep(500);

        // Question 4
        onView(withId(R.id.question4Dropdown)).perform(click());
        sleep(500);
        onData(allOf(is(instanceOf(String.class)), is("4 (Always)"))).perform(click());
        sleep(500);

        sleep(4000);
    }

    public void setTime(int hour, int minutes) {
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hour, minutes));
    }

    public void backToUserHome(){
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(isRoot()).perform(ViewActions.pressBack());

    }

    public static ViewAction swipeDownSlow() {
        return new GeneralSwipeAction(Swipe.SLOW, GeneralLocation.BOTTOM_CENTER,
                GeneralLocation.TOP_CENTER, Press.FINGER
        );
    }

    public void sleep(int time){
        SystemClock.sleep(time);
    }
}