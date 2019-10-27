package com.example.apptoast;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.concurrent.TimeUnit;

import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import static android.support.test.espresso.Espresso.onView;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;

import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ToastTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testToast() {


        onView(withId(R.id.editText)).perform(typeText(("TEST"))).perform(closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.button))
                .check(matches(withText("Done")));
        delay(2000);
    }

    @Test
    public void testAlert() throws InterruptedException, UiObjectNotFoundException {


        //onView(withId(R.id.)).perform(typeText(("TEST"))).perform(closeSoftKeyboard());
        onView(withId(R.id.other_button)).perform(click());
        onView(withText("Вы хотите закрыть приложение?")).check(matches(isDisplayed()));
        Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        UiObject obj = device.findObject(new UiSelector().textContains("No").clickable(true));
        obj.click();
        delay(2000);
    }

    @Test
    public void testRating() {
        onView(withId(R.id.ratingBar)).perform(click());

        onView(withId(R.id.textView))
                .check(matches(withText("Значение: 3.0")));
        delay(2000);

    }

    @Test
    public void testNewTab() {
        onView(withId(R.id.newtab_button)).perform(click());
        Espresso.pressBack();
        onView(withId(R.id.newtab_button))
                .check(matches(withText("NewTab")));
        delay(2000);
    }


    public void tearDown() throws Exception {

    }

    private void delay(long i) {

        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
