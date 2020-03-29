package com.twelve.latesleeper.test.automated;

import android.os.SystemClock;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import androidx.test.espresso.NoMatchingViewException;
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
import com.twelve.latesleeper.activity.MainActivity;

import junit.framework.AssertionFailedError;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class CreateNewGoalTest {

    @Rule
    public ActivityTestRule<MainActivity> CreateNewGoalTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

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
    public void testCreateNewGoal(){

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

        // UserHomeActivity
        sleep(4000);

        onView(withId(R.id.journalBtn)).perform(click());

/*        // CreateGoalActivity
        sleep(3000);

        onView(withId(R.id.createGoalScrollView)).perform(swipeDownSlow());
        onView(withId(R.id.createGoalScrollView)).perform(swipeDownSlow());

        onView(withId(R.id.continueButton)).perform(click());*/

        // CreateGoalSettingsActivity
        sleep(2000);

        setTime(21,00);

        sleep(2000);

        onView(withId(R.id.goalSettingsScrollView)).perform(swipeDownFast());

        numberPickerAnimation();

        sleep(2000);

        onView(withId(R.id.goalSettingsScrollView)).perform(swipeDownFast());

        onView(withId(R.id.button2)).perform(click());

        sleep(2000);

        // Swipe down to see the goals that have been set
        onView(withId(R.id.goalListView)).perform(swipeDownFast());
        onView(withId(R.id.goalListView)).perform(swipeDownFast());

        sleep(4000);

        onView(isRoot()).perform(ViewActions.pressBack());

        // UserHomeActivity
        sleep(4000);

        onView(withId(R.id.signOut)).perform(click());

        // LogInActivity
        sleep(4000);
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

    public void numberPickerAnimation(){
        ViewInteraction numPicker = onView(withId(R.id.numberPicker));
        numPicker.perform(setNumber(2));
        sleep(500);
        numPicker.perform(setNumber(3));
        sleep(500);
        numPicker.perform(setNumber(4));
        sleep(500);
        numPicker.perform(setNumber(2));
    }

    public void setTime(int hour, int minutes) {
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hour, minutes));
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

    public static ViewAction swipeDownSlow() {
        return new GeneralSwipeAction(Swipe.SLOW, GeneralLocation.BOTTOM_CENTER,
                GeneralLocation.TOP_CENTER, Press.FINGER
        );
    }

    public static ViewAction swipeDownFast() {
        return new GeneralSwipeAction(Swipe.FAST, GeneralLocation.BOTTOM_CENTER,
                GeneralLocation.TOP_CENTER, Press.FINGER
        );
    }

    public void sleep(int time){
        SystemClock.sleep(time);
    }
}