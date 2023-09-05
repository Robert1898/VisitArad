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
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.visitarad.evenimente.Evenimente;
import com.example.visitarad.hoteluri.Hoteluri;
import com.example.visitarad.magazine.CentreComerciale;
import com.example.visitarad.obiective.ObiectiveTuristice;
import com.example.visitarad.restaurante.Restaurante;
import com.example.visitarad.sanatate.Sanatate;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(AndroidJUnit4.class)
public class NavigareTest {

    @Rule
    public ActivityScenarioRule<PaginaPrincipala> activityScenarioRule = new ActivityScenarioRule<>(PaginaPrincipala.class);

    @Before
    public void setUp() {
        init(); // Inițializează Intents
    }

    @After
    public void cleanUp() {
        release(); // Eliberează Intents
    }

    @Test
    public void testDeconectare(){
        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());

        onView(withText("Deconectare")).perform(click());
        onView(withText("Ești sigur că vrei să te deconectezi?")).check(matches(isDisplayed()));
        onView(withId(android.R.id.button1)).perform(click());
        intended(hasComponent(MainActivityLogin.class.getName()));
    }

    @Test
    public void testIesireAplicatie(){
        pressBack();
        onView(withText("Esti sigur ca vrei sa iesi?")).check(matches(isDisplayed()));
        onView(withId(android.R.id.button1)).perform(click());
    }

    @Test
    public void testClickObiective(){
        onView(withId(R.id.idObTuristice)).perform(click());
        intended(hasComponent(ObiectiveTuristice.class.getName()));
    }

    @Test
    public void testClickRestaurante(){
        onView(withId(R.id.cardViewRestaurante)).perform(click());
        intended(hasComponent(Restaurante.class.getName()));
    }

    @Test
    public void testClickHoteluri(){

        onView(withId(R.id.cardViewHoteluri)).perform(scrollTo());
        onView(withId(R.id.cardViewHoteluri)).perform(click());
        intended(hasComponent(Hoteluri.class.getName()));
    }

    @Test
    public void testClickSanatate(){
        onView(withId(R.id.cardViewSanatate)).perform(scrollTo());
        onView(withId(R.id.cardViewSanatate)).perform(click());
        intended(hasComponent(Sanatate.class.getName()));
    }

    @Test
    public void testClickEvenimente(){
        onView(withId(R.id.cardViewEvenimete)).perform(scrollTo());
        onView(withId(R.id.cardViewEvenimete)).perform(click());
        intended(hasComponent(Evenimente.class.getName()));
    }

    @Test
    public void testClickMagazine(){
        onView(withId(R.id.cardViewMagazine)).perform(scrollTo());
        onView(withId(R.id.cardViewMagazine)).perform(click());
        intended(hasComponent(CentreComerciale.class.getName()));
    }

}
