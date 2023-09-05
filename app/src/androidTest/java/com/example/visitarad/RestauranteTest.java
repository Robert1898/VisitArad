package com.example.visitarad;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.init;
import static androidx.test.espresso.intent.Intents.release;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.visitarad.evenimente.Evenimente;
import com.example.visitarad.hoteluri.Hoteluri;
import com.example.visitarad.magazine.CentreComerciale;
import com.example.visitarad.obiective.ObiectiveTuristice;
import com.example.visitarad.restaurante.Restaurant;
import com.example.visitarad.restaurante.Restaurante;
import com.example.visitarad.sanatate.Sanatate;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(AndroidJUnit4.class)
public class RestauranteTest {

    @Rule
    public ActivityScenarioRule<Restaurante> mActivityRule = new ActivityScenarioRule<>(
            Restaurante.class);

    @Test
    public void testRecyclerViewVizibil() {
        onView(withId(R.id.recyclerViewRestaurante)).check(matches(isDisplayed()));
    }

    @Test
    public void testClickOnRecyclerViewItem() {
        onView(withId(R.id.recyclerViewRestaurante)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        intended(hasComponent(Restaurant.class.getName()));
    }

}
