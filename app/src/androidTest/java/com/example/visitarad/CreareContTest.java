package com.example.visitarad;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.init;
import static androidx.test.espresso.intent.Intents.release;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(AndroidJUnit4.class)
public class CreareContTest {

    @Rule
    public ActivityScenarioRule<CreareCont> activityRule = new ActivityScenarioRule<>(CreareCont.class);


    @Before
    public void setUp() {
        init(); // Inițializează Intents
    }

    @After
    public void cleanUp() {
        release(); // Eliberează Intents
    }


    @Test
    public void testAdaugareEmailSiParola(){
        onView(withId(R.id.txtEmailCreareCont)).perform(click());
        onView(withId(R.id.txtEmailCreareCont)).perform(typeText("test@gmail.com"));
        closeSoftKeyboard();

        onView(withId(R.id.txtPassCreareCont)).perform(click());
        onView(withId(R.id.txtPassCreareCont)).perform(typeText("testaplicatie"));

        closeSoftKeyboard();

        onView(withId(R.id.butonCreareCont)).perform(click());
    }

    @Test
    public void testBackPagina(){
        pressBack();
        intended(hasComponent(PaginaPrincipala.class.getName()));
    }

}
