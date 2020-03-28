package com.twelve.latesleeper.test.automated;

import android.os.SystemClock;
import android.util.Log;
import android.widget.TimePicker;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.GeneralLocation;
import androidx.test.espresso.action.GeneralSwipeAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Swipe;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

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
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;

public class FourStepsTest {
    @Rule
    public ActivityTestRule<MainActivity> FourStepsTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private String emailAddress, password;

    private UiDevice device;

    @Before
    public void setUp() throws Exception {
        emailAddress = "tfouxman@gmail.com";
        password = "test123";
        device = UiDevice.getInstance(getInstrumentation());

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testStartGoal() throws UiObjectNotFoundException {

        goToLogin();

        sleep(3000);

        try {
            onView(withId(R.id.signOut)).perform(click());
            sleep(3000);
        } catch (NoMatchingViewException e) {

        }

        fillForm();
        sleep(5000);
        onView(withId(R.id.logInButton)).perform(click());
        Log.d("SUCCESS","Login successful");

        // UserHomeActivity
        sleep(2000);

        goToGoal();

        onView(withId(R.id.startGoal)).perform(click());

        // Relabel
        relabelInput();

        onView(withId(R.id.nextButton)).perform(click());

        sleep(2000);

        Log.d("SUCCESS","Relabel successful");

        // Reframe
        reframeInput();

        onView(withId(R.id.nextButton)).perform(scrollTo(), click());

        Log.d("SUCCESS","Reframe successful");

        // Refocus
        sleep(3000);

        onView(withId(R.id.nextButton)).perform(click());

        sleep(3000);

        Log.d("SUCCESS","Refocus successful");

        sleep(200000);

        testNotification();

        sleep(3000);


        setTime(22, 45);

        sleep(2000);

        onView(withId(R.id.setAlarmBtn)).perform(click());

        sleep(2000);
//
//        backToUserHome();

//        onView(withId(R.id.signOut)).perform(click());

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
        onData(allOf(is(instanceOf(String.class)), is("2 (Sometimes)"))).perform(scrollTo(), click());
        sleep(500);

        // Question 2
        onView(withId(R.id.question2Dropdown)).perform(click());
        sleep(500);
        onData(allOf(is(instanceOf(String.class)), is("4 (Always)"))).perform(scrollTo(), click());
        sleep(500);

        // Question 3
        onView(withId(R.id.question3Dropdown)).perform(scrollTo(), click());
        sleep(500);
        onData(allOf(is(instanceOf(String.class)), is("3 (Often)"))).perform(scrollTo(), click());
        sleep(500);

        // Question 4
        onView(withId(R.id.question4Dropdown)).perform(scrollTo(), click());
        sleep(500);
        onData(allOf(is(instanceOf(String.class)), is("4 (Always)"))).perform(scrollTo(), click());
        sleep(500);

        // Question 5
        onView(withId(R.id.question5Dropdown)).perform(scrollTo(), click());
        sleep(500);
        onData(allOf(is(instanceOf(String.class)), is("4 (Always)"))).perform(scrollTo(), click());
        sleep(500);

        // Question 6
        onView(withId(R.id.question6Dropdown)).perform(scrollTo(), click());
        sleep(500);
        onData(allOf(is(instanceOf(String.class)), is("4 (Always)"))).perform(scrollTo(), click());
        sleep(500);

        // Question 7
        onView(withId(R.id.question7Dropdown)).perform(scrollTo(), click());
        sleep(500);
        onData(allOf(is(instanceOf(String.class)), is("4 (Always)"))).perform(scrollTo(), click());
        sleep(500);

        // Question 8
        onView(withId(R.id.question8Dropdown)).perform(scrollTo(), click());
        sleep(500);
        onData(allOf(is(instanceOf(String.class)), is("4 (Always)"))).perform(scrollTo(), click());
        sleep(500);

        // Question 9
        onView(withId(R.id.question9Dropdown)).perform(scrollTo(), click());
        sleep(500);
        onData(allOf(is(instanceOf(String.class)), is("4 (Always)"))).perform(scrollTo(), click());
        sleep(500);

        // Question 10
        onView(withId(R.id.question10Dropdown)).perform(scrollTo(), click());
        sleep(500);
        onData(allOf(is(instanceOf(String.class)), is("4 (Always)"))).perform(scrollTo(), click());
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

    public void testNotification() throws UiObjectNotFoundException {

        Log.d("TESTING","Testing notification click");

        String NOTIFICATION_TITLE = "TIME FOR BED";
        String NOTIFICATION_TEXT = "Remember your goal!";

        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.pressHome();
        sleep(5000);
        device.openNotification();
        device.wait(Until.hasObject(By.text(NOTIFICATION_TITLE)), 30000);
        UiObject2 title = device.findObject(By.text(NOTIFICATION_TITLE));
        UiObject2 text = device.findObject(By.text(NOTIFICATION_TEXT));
        assertEquals(NOTIFICATION_TITLE, title.getText());
        assertEquals(NOTIFICATION_TEXT, text.getText());
        title.click();
        sleep(4000);
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