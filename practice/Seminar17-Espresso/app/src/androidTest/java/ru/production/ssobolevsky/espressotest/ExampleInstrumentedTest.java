package ru.production.ssobolevsky.espressotest;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    public static final String[] names = {
            "Person one",
            "Person two",
            "Person three",
            "Person four",
            "Person five",
            "Person six",
            "Person seven",
            "Person eight",
            "Person nine",
            "Person ten"
    };

    @Test
    public void buttonEnabledTest() {
        onView(withId(R.id.add)).check(matches(not(isEnabled())));
        onView(withId(R.id.tv_input)).perform(typeText("Some text"));
        onView(withId(R.id.add)).check(matches(isEnabled()));
        onView(withId(R.id.tv_input)).perform(clearText());
        onView(withId(R.id.add)).check(matches(not(isEnabled())));
    }

    @Test
    public void addToListTest() {
        for (String name : names) {
            onView(withId(R.id.tv_input)).perform(typeText(name));
            onView(withId(R.id.add)).perform(click());
        }

        onView(withId(R.id.to_all)).perform(click());

        for (int i = 0; i < names.length; i++) {
            onView(withId(R.id.rv_names)).perform(RecyclerViewActions.<NamesAdapter.MyViewHolder>actionOnItemAtPosition(i, click()));
            pressBack();
        }
    }
}
