package com.twelve.latesleeper.test.automated;

import android.os.SystemClock;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.GeneralLocation;
import androidx.test.espresso.action.GeneralSwipeAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Swipe;
import androidx.test.espresso.action.ViewActions;
import androidx.test.rule.ActivityTestRule;

import com.twelve.latesleeper.R;
import com.twelve.latesleeper.activity.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;
import static org.junit.Assert.*;

public class StartGoalTest {
    @Rule
    public ActivityTestRule<MainActivity> CreateAccountActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

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
        goToLogin();

        fillForm();

        onView(withId(R.id.logInButton)).perform(click());

        // UserHomeActivity
        sleep(4000);

        onView(withId(R.id.newEntry)).perform(click());

        // ViewGoalsActivity
        sleep(4000);

        onData(anything()).inAdapterView(withId(R.id.goalListView)).atPosition(2).perform(click());

        // ViewGoalActivity
        sleep(4000);

        onView(withId(R.id.viewGoalScrollView)).perform(swipeDownSlow());

        sleep(1000);

        onView(withId(R.id.startGoal)).perform(click());

        sleep(4000);

        onView(isRoot()).perform(ViewActions.pressBack());
        sleep(1000);
        onView(isRoot()).perform(ViewActions.pressBack());
        sleep(1000);
        onView(isRoot()).perform(ViewActions.pressBack());
        sleep(500);

        onView(withId(R.id.signOut)).perform(click());

        sleep(2000);

    }

    public void goToLogin(){
        sleep(1000);
        onView(withId(com.twelve.latesleeper.R.id.imageView)).perform(doubleClick());
    }

    public void fillForm(){
        onView(withId(com.twelve.latesleeper.R.id.txtEmail)).perform(typeText(emailAddress));
        closeSoftKeyboard();

        onView(withId(R.id.txtPass)).perform(typeText(password));
        closeSoftKeyboard();
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